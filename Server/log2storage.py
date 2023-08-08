import os
from google.cloud import storage
from google.cloud import pubsub_v1

project_id = "jnu-idv-11"
bucket_name = "network-traffic-report-storage"
subscription_name = "network-traffic-report-sub"

# 네트워크 트래픽 로그 파일 경로(예시)
blob_name = "logs/network_traffic.log"

subscriber = pubsub_v1.SubscriberClient()
subscription_path = subscriber.subscription_path(project_id, subscription_name)

storage_client = storage.Client(project=project_id)
bucket = storage_client.bucket(bucket_name)
blob = bucket.blob(blob_name)

def callback(message):
    network_traffic_log = message.data.decode("utf-8")

    # 네트워크 트래픽 로그 데이터를 로그 파일에 추가
    current_log = blob.download_as_text() if blob.exists() else ""
    updated_log = current_log + "\n" + network_traffic_log
    blob.upload_from_string(updated_log, content_type="text/plain")
    message.ack()
    # blob.upload_from_string(log_data, content_type="text/plain") --> 고정 데이터 추가시
subscriber.subscribe(subscription_path, callback=callback)

# 네트워크 트래픽 로그 데이터 생성 및 저장
network_traffic_log = "Network traffic log entry."
callback(network_traffic_log)