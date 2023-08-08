# 'token.json'을 사용하여 Gmail API에 인증하고 트래픽 알림 이메일 읽가

import base64
from googleapiclient.discovery import build
from auth import get_gmail_credentials

def read_latest_email():
    creds = get_gmail_credentials()
    service = build('gmail', 'v1', credentials=creds)
    result = service.users().messages().list(userId='me', q='is:unread', maxResults=1).execute()
    messages = result.get('messages', [])
    if not messages:
        return None
    msg_id = messages[0]['id']
    message = service.users().messages().get(userId='me', id=msg_id).execute()
    raw_data = message['payload']['headers']
    for values in raw_data:
        name = values['name']
        if name == 'From':
            from_email = values['value']
        elif name == 'Subject':
            subject = values['value']

    # 메세지 디코딩
    message_data = base64.urlsafe_b64decode(message['payload']['body']['data']).decode("utf-8")
    return {
        'from': from_email,
        'subject': subject,
        'body': message_data
    }

# 'read_latest_email' 함수 수정
# 위 함수에서 'q='is:unread' 부분을 통해 읽지 않은 이메일을 가져오고 있습니다.
# 만약 이메일을 특정 조건으로 필터링하거나 더 많은 이메일을 가져와야 한다면 'q' 매개 변수를 수정해야 합니다.

# 알림 메세지 내용 수정
# 'alert_message' 변수에 저장되는 알림 메세지 내용을 원하는 형식에 맞게 수정해야 합니다

# Flask 앱 실행 설정
# 'app.run(host='0.0.0.0', port=8080)' 부분은 개발 환경에서 사용할 수 있는 설정이고,
# 실제 배포 환경에서는 이를 적절히 수정해야 합니다.

# 알림 발송 로직 추가
# 현재 코드는 알림 메세지를 구성하여 반환하고 있지만, 실제로 관리자에게 알림을 발송하는 메커니즘을 추가해야 합니다.
# 이메일 발송 라이브러리를 사용하거나 다른 알림 메커니즘을 구현해야 합니다.