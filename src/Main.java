import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        // 변수 선언
        int[] comNumber = new int[3]; // 컴퓨터 숫자 배열
        int[] playerNumber = new int[3]; // 플레이어 숫자 배열
        int insertNumber; // 플레이어에게 입력받은 세자리 숫자
        int count = 0; // 시도 횟수 초기 선언
        int strike = 0; // 스트라이크 초기 선언
        int ball = 0; // 볼 초기 선언
        Scanner sc = new Scanner(System.in); // 입력 선언

        // 컴퓨터 랜덤 숫자 3자리 생성
        for(int i = 0; i < 3; i++){
            comNumber[i] = (int)(Math.random() * 10); // 0~9까지 숫자 중 랜덤 숫자를 생성하고 int형으로 변환
            for(int j = 0; j < i; j++){
                if(comNumber[i]==comNumber[j]){ // 중복된 숫자가 나온 경우 다시 생성
                    i--;
                    break;
                }
            }
        }
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        // 성공할때까지 반복하는 조건
        while(true){
            count++; // 시도 횟수 증가
            System.out.print(count+"번째 시도 : "); // 시도 횟수 표시

            // 플레이어 숫자 입력
            insertNumber = sc.nextInt();
            // 입력된 숫자 각각 나눠서 배열로 저장 (일의 자리부터 배열 0번에 저장됨)
            for(int i = 0; i < playerNumber.length; i++) {
                playerNumber[i] = insertNumber % 10;
                insertNumber /= 10;
            }

            // 스트라이크, 볼 판단
            for(int i = 0; i < 3; i++){ // 각 자리별 컴퓨터와 플레이어 숫자가 같으면 스트라이크 증가
                if(comNumber[i] == playerNumber[2-i]){ // 플레이어 숫자는 일의자리부터, 컴퓨터 숫자는 백의자리부터 저장되어 있음
                    strike++;
                }
                else{
                    for(int j = 0; j < 3; j++){ // 스트라이크 아닌 경우 중, 컴퓨터와 플레이어 숫자가 같은 경우 볼 증가
                        if(comNumber[i] == playerNumber[2-j]) {
                            ball++;
                        }
                    }
                }
            }

            // 볼, 스트라이크 출력
            if((strike == 0 && ball == 0) || (strike != 0 && ball != 0)) {
                System.out.println(ball + "B" + strike + "S");
            }
            else if(strike == 0) {
                System.out.println(ball + "B"); // 스트라이크 0일때 볼만 출력
            }
            else {
                System.out.println(strike + "S"); // 볼 0일때 스트라이크만 출력
            }

            // 스트라이크가 3인 경우 게임 종료
            if(strike == 3){
                System.out.println(count + "번만에 맞히셨습니다.\n게임을 종료합니다.");
                break;
            }
            else{ // 정답이 아닌 경우 다시 시도하기 위한 스트라이크, 볼 초기화
                strike = 0; // 스트라이크 초기화
                ball = 0; // 볼 초기화
            }
        }
    }
}