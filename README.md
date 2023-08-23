<div align="center">
  <img src="https://capsule-render.vercel.app/api?type=transparent&fontColor=9370DB&text=kipl👶🏻%20&height=150&fontSize=45" alt="transparent" />
</div>

<p align="center">AI/빅데이터 기반의 영유아 대상 식단 추천 서비스, <strong>키플</strong></p>
<p align="center"><a href="https://github.com/Latencygg/kipl"><img src="https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2FLatencygg%2Fkipl&count_bg=%23000000&title_bg=%23000000&icon=github.svg&icon_color=%23E7E7E7&title=Github&edge_flat=false"/></a></p>

***

### 🔖 소개
  - 키플(kipl)은 키즈 플레이트(Kid's Plate)의 줄임말로, AI/빅데이터 기반 영유아 대상 식단 추천 서비스입니다.
  - 비지도학습으로 군집화된 데이터를 카테고리화하여 만든 AI 모델을 통해 사용자의 요구 사항이 반영된 결과를 제공합니다.

### 💡 서비스 목적
  - 연령대별 최적의 성장 및 발달을 위한 양질의 데이터를 제공합니다.
  - AI(머신러닝 & 딥러닝)을 활용한 최적의 데이터를 제공합니다.
  - 가정에서는 물론, 어린이집이나 아동 보호소 등 다양한 장소 및 분야에서 사용할 수 있도록 활용도 높은 서비스를 제공합니다.

### 🏃🏻 프로젝트 목표
  - Google Cloud Platform의 서비스들을 적재적소에 활용하여 프로젝트의 완성도를 높입니다.
  - Google Workspace, Github와 같은 협업 서비스를 사용하여 활발한 소통을 바탕으로 효율적인 협업을 통해 공동의 목표에 달성합니다.

### 📚 기술 스택
![Static Badge](https://img.shields.io/badge/Google%20Cloud%20Platform-%234285F4?style=flat&logo=googlecloud&logoColor=white&labelColor=%234285F4)
![Static Badge](https://img.shields.io/badge/Python-%233776AB?logo=python&logoColor=white&labelColor=%233776AB)
![Static Badge](https://img.shields.io/badge/TensorFlow-%23FF6F00?logo=tensorflow&logoColor=white&labelColor=%23FF6F00)
![Static Badge](https://img.shields.io/badge/Keras-%23D00000?style=flat&logo=keras&logoColor=white&labelColor=%23D00000)
![Static Badge](https://img.shields.io/badge/MySQL-%234479A1?style=flat&logo=mysql&logoColor=white&labelColor=%234479A1)
![Static Badge](https://img.shields.io/badge/pandas-%23150458?style=flat&logo=pandas&logoColor=white&labelColor=%23150458)
![Static Badge](https://img.shields.io/badge/Apache%20Beam-%23E25A1C?style=flat&logoColor=white&labelColor=%23E25A1C)
![Static Badge](https://img.shields.io/badge/Java-%23E85C33?labelColor=%23E85C33)
![Static Badge](https://img.shields.io/badge/Spring%20Boot-%236DB33F?style=flat&logo=springboot&logoColor=white&labelColor=%236DB33F)
![Static Badge](https://img.shields.io/badge/GitHub-%23181717?logo=git&logoColor=white&labelColor=%23181717)

### 📢 프레젠테이션
  - [Google Slides](https://docs.google.com/presentation/d/1N2k06W986UxusSEhcLNO-zW8Q9qEfn_TwxYo4GF4fuE/edit?usp=sharing)

### 🗓️ 개발 일정
  - [Project Backlog](https://github.com/users/Latencygg/projects/3)
  - [Issue Control](https://github.com/Latencygg/kipl/issues)
  - [Google Spreadsheets](https://docs.google.com/spreadsheets/d/1K13jrMJEbruGH6SgpTZyGPpVdJmgOs0t4wgxs4v66hs/edit?usp=sharing)

### 📌 WBS

<img src="https://github.com/Latencygg/kipl/blob/main/pic/wbs.png" width="auto" height="auto">

### 📑 ERD

#### Scenario
<img src="https://github.com/Latencygg/kipl/blob/main/pic/scenario8.PNG" width="auto" height="auto">
<img src="https://github.com/Latencygg/kipl/blob/main/pic/scenario1.PNG" width="auto" height="auto">

#### DB Schema
<img src="https://github.com/Latencygg/kipl/blob/main/pic/kipl_dataset_schema.png" width="auto" height="auto">

### 🔗 API
  - 영유아의 개월수를 기준으로 세분화한 식단 제공 서비스
  - 알러지 필터링 서비스
  - 기존 데이터에 없던 식단 추가 리포트 요청
  - 추천받은 식단에 대한 👍🏻(Good) & 👎🏻(Bad) 평가
  - 딥러닝 모델 기반 잘못된 데이터 입력에 대한 방어

### 🖥️ 프로토타입

### 🔑 기술적 이슈와 해결 과정

  #### Dataflow Runner
  - Dataflow API 이용 시 Direct Runner는 동작이 가능하나, Dataflow Runner는 동작이 실패하였음
  - 위 문제를 Pcollection 생성 부분의 단계에서 beam.Create 대신 ReadFromText 함수를 사용하여 해결
  - 2개의 Pcollection을 합칠 때, 처리해야 할 데이터의 요소 합치기 문제 발생
  - Flatten() 함수를 사용하여 하나로 묶어진 데이터를, DoFn을 이용하여 요소를 합쳐 해결
  
  #### 서버 배포 과정
  
  #### 필터링 로직 구현 과정
  - Dataflow: Model의 picklable 문제로 인해 실패
  - Cloud Function: Model이 Cloud Function 내에 업로드가 불가능하여 실패
  - Vertex AI NLP Model + Prompt(PaLM 2): 모델을 서버 단위에서 불러와서 실행에 성공

### ❗️ 프로젝트 작업 시 유의사항

  #### ☑️ 커밋 메세지 컨벤션
  - `Tag: YYMMDD_내용` 형식으로 커밋 메세지를 작성해주세요.
  - 커밋 메세지 내용은 간결하게 작성하고, <strong>Extended description</strong> 란에 수정사항을 작성해주세요.

| Tag | Mean |
| --- | --- |
| feature | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 포맷 수정 |
| test | 테스트 코드 추가 |
| refactoring | 코드 리팩토링 |
