# 트래픽 알림 이메일을 분석하고, 필요한 조건을 충족하는 경우 관리자에게 알림

# # 가상 환경 생성
# python3 -m venv myenv
# 가상 환경 활성화(VSCode Terminal에서 실행할 것)
# source myenv/bin/activate
# 가상 환경에서 Flask 설치
# pip install Flask
# # 가상 환경 비활성화
# deactivate

from flask import Flask, jsonify
from read_gmail import read_latest_email

app = Flask(__name__)

@app.route('/send_alert_via_gmail', methods=['GET'])
def send_alert_via_gmail():
    email_info = read_latest_email()
    if email_info:
        # 관리자에게 보내지는 알림 메세지 구성
        alert_message = f"From: {email_info['from']}\nSubject: {email_info['subject']}\nBody: {email_info['body']}"

        return jsonify({'status': 'success'})
    else:
        return jsonify({'status': 'no_new_email'})
    
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)