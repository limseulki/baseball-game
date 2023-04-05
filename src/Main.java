import java.util.Scanner;
// 구현 기능 list
// 랜덤 숫자 만들기 OK
// 한자리 숫자에 대해 볼, 스트라이크 판단하는 부분 구현하기 OK
// 볼, 스트라이크를 표현하는 부분 구현하기 OK
// 게임 종료하는 부분 구현하기 OK
public class Main {
    public static void main(String[] args) {
        // 변수 선언
        int[] comNumber = new int[3]; // 컴퓨터 숫자
        int[] playerNumber = new int[3]; // 플레이어 숫자
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
        for(int i = 0; i < 3; i++){
            System.out.print(comNumber[i]); // 컴퓨터 숫자 테스트 출력
        }
        System.out.println();
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        // 성공할때까지 반복하는 조건
        while(true){
            count++; // 시도 횟수 증가
            System.out.print(count+"번째 시도 : "); // 시도 횟수 표시

            // 플레이어 숫자 입력
            for(int i = 0; i < playerNumber.length; i++){ // 배열 크기만큼 반복
                playerNumber[i] = sc.nextInt(); // 숫자 3개 입력받기
                for(int j = 0; j < i; j++){
                    if(playerNumber[j] == playerNumber[i]){ // 중복된 숫자 입력시 재입력
                        System.out.println("중복된 값을 입력하였습니다.");
                        i--;
                        break;
                    }
                }
            }

            // 스트라이크, 볼 판단
            for(int i = 0; i < 3; i++){ // 각 자리별 컴퓨터와 플레이어 숫자가 같으면 스트라이크 증가
                if(comNumber[i] == playerNumber[i]){
                    strike++;
                }
                else{
                    for(int j = 0; j < 3; j++){ // 스트라이크 아닌 경우 중, 컴퓨터와 플레이어 숫자가 같은 경우 볼 증가
                        if(comNumber[i] == playerNumber[j]) {
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
// 오류 01
// 컴퓨터 숫자 생성시 for문 안에서 출력하니까 중복된 경우 쌓인 데이터도 함께 나옴. 4,5,5,5,3 이렇게
// 오류 02
// 입력받은 숫자 각 자릿수별로 나눠 배열에 저장하기
// 0으로 시작하는 수 입력하면 에러남. 자릿수 하나씩 없애다 0만 남을 때가 문제인 듯.
//        ArrayList<Integer> arrNum = new ArrayList<Integer>(3);
//        while(playerNumber > 0) {
//            arrNum.add(playerNumber % 10);
//            playerNumber /= 10;
//        }