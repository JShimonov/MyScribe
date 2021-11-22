import os
import sys
from google.cloud import speech, storage

os.environ['GOOGLE_APPLICATION_CREDENTIALS'] = 'client_service_key.json'
speech_client = speech.SpeechClient()

# Example 1. Transcribe local media file
# file size < 10mbs, Length < 1 minute

## Step #1. Load media files
media_file_name_mp3 = 'hello_test.mp3'

with open(media_file_name_mp3, 'rb') as f1:
    byte_data_mp3 = f1.read()
audio_mp3 = speech.RecognitionAudio(content=byte_data_mp3)

## Step #2, Configure Media FIles Output
config_mp3 = speech.RecognitionConfig(
    sample_rate_hertz = 48000,
    enable_automatic_punctuation = True,
    language_code = 'en-US'
)

## Step #3. Transcribing the RecognitionAudio objects
response_standard_mp3 = speech_client.recognize(
    config = config_mp3,
    audio = audio_mp3
)

#print(response_standard_mp3)


# Example 3: Transcribing a long media file
media_uri = 'gs://speech-to-text-media-scribes/steve_test.mp3'
long_audio_mp3 = speech.RecognitionAudio(uri = media_uri)

config_mp3_enhanced = speech.RecognitionConfig(
    sample_rate_hertz = 48000,
    enable_automatic_punctuation = True,
    language_code = 'en-US',
    use_enhanced = True,
    model = 'video'
)

operation = speech_client.long_running_recognize(
    config = config_mp3,
    audio = long_audio_mp3
)
# response = operation.result(timeout=90)
# print(response)



def transcribe_gcs_with_word_time_offsets(gcs_uri):
    """Transcribe the given audio file asynchronously and output the word time
    offsets."""
    from google.cloud import speech

    client = speech.SpeechClient()

    audio = speech.RecognitionAudio(uri=gcs_uri)
    config = speech.RecognitionConfig(
        #encoding=speech.RecognitionConfig.AudioEncoding.FLAC,
        encoding = speech.RecognitionConfig.AudioEncoding.ENCODING_UNSPECIFIED,
        sample_rate_hertz=16000,
        enable_automatic_punctuation = True,
        language_code="en-US",
        enable_word_time_offsets=True,
        model = 'video'
    )

    operation = client.long_running_recognize(config=config, audio=audio)

    print("Waiting for operation to complete...")
    result = operation.result(timeout=90)

    for result in result.results:
        alternative = result.alternatives[0]
        print("Transcript: {}".format(alternative.transcript))
        print("Confidence: {}".format(alternative.confidence))

        for word_info in alternative.words:
            word = word_info.word
            start_time = word_info.start_time
            # end_time = word_info.end_time

            print(
                f"Word: {word}, timestamp: {start_time.total_seconds()}"
            )

def upload_blob(bucket_name, source_file_name, destination_blob_name):
    # The ID of your GCS bucket
    # bucket_name = "your-bucket-name"
    # The path to your file to upload
    # source_file_name = "local/path/to/file"
    # The ID to your GCS object
    # destination_blob_name = "storage-object-name"

    storage_client = storage.Client()
    bucket = storage_client.bucket(bucket_name)
    blob = bucket.blob(destination_blob_name)

    blob.upload_from_filename(source_file_name)

    print(
        'File {} uploaded to {}'.format(
            source_file_name, destination_blob_name
        )
    )

transcribe_gcs_with_word_time_offsets(media_uri)