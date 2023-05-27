import csv
import os

# 현재 스크립트 파일의 경로를 가져옴
script_dir = os.path.dirname(os.path.abspath(__file__))

# 입력 파일과 출력 파일의 상대 경로를 지정
input_file = os.path.join(script_dir, 'book.csv')
output_file = os.path.join(script_dir, 'book2.csv')

# cp949로 인코딩된 CSV 파일을 UTF-8로 변환하여 새로운 파일에 저장
with open(input_file, 'r', encoding='cp949', newline='') as file_in, open(output_file, 'w', encoding='utf-8', newline='') as file_out:
    reader = csv.reader(file_in)
    writer = csv.writer(file_out)

    # 헤더를 그대로 복사
    headers = next(reader)
    writer.writerow(headers)

    # 각 행을 읽어 UTF-8로 인코딩하여 새로운 파일에 쓰기
    for row in reader:
        encoded_row = [cell.encode('cp949').decode('utf-8') for cell in row]
        writer.writerow(encoded_row)

print("변환이 완료되었습니다.")
