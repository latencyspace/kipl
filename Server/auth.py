# 사용자 인증 진행 및 'token.json' 파일에 액세스 토큰 저장

# VSCode 터미널에서 가상 환경 활성화
# source myenv/bin/activate (*myenv는 가상 환경 이름명임)
# Gmail API를 사용하기 위해 터미널에서 라이브러리 설치하기
# pip install google-auth google-auth-oauthlib google-auth-httplib2 google-api-python-client

# Gmail API 인증
import os
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
from google.oauth2.credentials import Credentials

SCOPES = ['https://www.googleapis.com/auth/gmail.readonly']

def get_gmail_credentials():
    creds = None
    # 저장된 인증 정보 불러오기
    if os.path.exists('token.json'):
        creds = Credentials.from_authorized_user_file('token.json', SCOPES)
    # 인증 정보 없는 경우(혹은 만료된 경우) 사용자에게 인증 요청하기
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file('credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)
        # 인증 정보 저장
        with open('token.json', 'w') as token:
            token.write(creds.to_json)
    return creds

# 'credentials.json' 파일 생성
# flow = InstalledAppFlow.from_client_secrets_file('credentials.json', SCOPES)
# 위 코드에서 'credentials.json' 파일이 필요합니다.
# Google Cloud Console에서 OAuth 클라이언트 ID를 만들어 JSON 파일을 다운로드 받아 프로젝트 디렉터리에 저장해야 합니다.

