import os
import sys
from google.cloud import speech
from google.cloud import storage

def main():
    os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'src\\keys\\client_service_key.json'
    file = sys.argv[1]

    dest_blob_name = short_dest(file)
    upload_blob(bucket_name='speech-to-text-media-scribes', source_file_name=file, destination_blob_name=dest_blob_name)
    
    media_uri = get_URI(file_name=dest_blob_name)
    print(transcribe_gcs_with_word_time_offsets(media_uri))

def short_dest(file_name):
    file_name = file_name[file_name.rfind('\\')+1:]
    return file_name

def get_URI(file_name):
    storage_client = storage.Client()
    bucket_name = 'speech-to-text-media-scribes'
    blob_name = file_name
    bucket = storage_client.get_bucket(bucket_name)
    blob = bucket.blob(blob_name)
    link = blob.path_helper(bucket_name, blob_name)
    link = link.replace("o/", "")
    return 'gs://' + link

def transcribe_gcs_with_word_time_offsets(gcs_uri):
    """Transcribe the given audio file asynchronously and output the word time
    offsets."""
    client = speech.SpeechClient()
    audio = speech.RecognitionAudio(uri=gcs_uri)
    config = speech.RecognitionConfig(
        encoding = speech.RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED,
        sample_rate_hertz=16000,
        enable_automatic_punctuation = True,
        language_code="en-US",
        enable_word_time_offsets=True,
        model = 'video'
    )
    operation = client.long_running_recognize(config=config, audio=audio)

    result = operation.result(timeout=90)

    for result in result.results:
        alternative = result.alternatives[0]
        for word_info in alternative.words:
            word = word_info.word
            start_time = word_info.start_time
            temp_text = word + ' - ' + str(start_time)
            print(temp_text)

def upload_blob(bucket_name, source_file_name, destination_blob_name):
    storage_client = storage.Client()
    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(destination_blob_name)

    blob.upload_from_filename(source_file_name)
    new_name = short_dest(source_file_name)
    bucket.rename_blob(blob, new_name)

if __name__ == "__main__":
    main()