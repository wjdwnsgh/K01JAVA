package ex17collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ex01start.E02SystemOutPrintln;


class Friend {
	//멤버변수
	String name;// 이름
	String phone;// 전화번호
	String addr; // 주소
	
	//생성자
	public Friend(String name, String phone, String addr) {
		this.name = name;
		this.phone = phone;
		this.addr = addr;
	}
	
	//멤버변수 전체정보 출력용 멤버메서드
	public void showAllData() {
		System.out.println("이름:"+ name);
		System.out.println("전화번호:"+ phone);
		System.out.println("주소:"+ addr);
	}
	
	
	public void showBasicInfo() {}
}

//고딩친구
class HighFriend extends Friend {
	//확장한 멤버변수
	String nickname; // 별명
	
	public HighFriend(String name, String phone, String addr, String nickname) {
		//부모클래스의 생성자 호출
		super(name, phone, addr);
		//자신의 멤버변수 초기화
		this.nickname = nickname;
	}
	
	
	public void showAllData() {
		System.out.println("==고딩친구(전체정보)==");
		super.showAllData();
		System.out.println("별명:"+ nickname);
	}
	
	
	@Override
	public void showBasicInfo()
	{
		System.out.println("==고딩친구==");
		System.out.println("별명:"+nickname);
		System.out.println("전번:"+ phone);
		
	}
}

//대딩친구
class UnivFriend extends Friend {
	String major; //자식 쪽에서 확장한 멤버변수. 전공.
	public UnivFriend(String name, String phone, String addr, String major) {
		super(name,phone, addr);
		this.major = major;
	}
	
	@Override
	public void showAllData()
	{
		System.out.println("==대딩친구(전체정보)==");
		super.showAllData();
		System.out.println("전공:"+ major);
	}
	
	@Override
	public void showBasicInfo()
	{
		System.out.println("==대딩친구==");
		System.out.println("이름:"+name);
		System.out.println("전화번호:"+phone);
	}
}


class FriendIndoHandler {
	
	//친구의 정보를 저장할 List컬렉션 생성
	ArrayList<Friend> lists;
	
	//생성자
	public FriendIndoHandler(int num) {
		
		//멤버변수가 컬렉션으로 변경되었으므로 List<T>를 생성한다.
		lists = new ArrayList<Friend>();
	}
	
	//새로운 친구 연락처 추가
	public void addFriend(int choice) {
		//choice가 1이면 고딩, 2면 대딩친구 추가
		
		//정보 인력을 위한 객체생성
		Scanner scan = new Scanner(System.in);
		
		String iName, iPhone, iAddr, iNickname, iMajor;
		//기본정보 입력(연락처의 공통사항)
		System.out.println("이름:"); iName = scan.nextLine();
		System.out.println("전화번호:"); iPhone = scan.nextLine();
		System.out.println("주소:"); iAddr = scan.nextLine();
		
		if(choice == 1) {
			System.out.println("별명:"); iNickname = scan.nextLine();
			
			//고딩친구 객체를 만든 후 컬렉션에 add한다. 인덱싱은 필요없다.
			HighFriend high = new HighFriend(iName, iPhone, iAddr, iNickname);
			lists.add(high);
		}
		else if(choice == 2) {
			System.out.println("전공:"); iMajor = scan.nextLine();
			
			//대딩친구 객체를 생성과 동시에 add한다.
			lists.add(new UnivFriend(iName, iPhone, iAddr, iMajor));
		}
		
		System.out.println("친구정보 입력이 완료되었습니다.");
	}
	
	/*
	일반 for문을 통해 컬렉션에 접근한다. 이때에는 인덱스를 사용하므로
	get() 메서드를 활용한다. 컬렉션에 저장된 객체의 갯수는 size()메서드를
	통해 알 수 있다.
	 */
	public void showAllData() { //전체정보출력용 메서드
		for(int i =0; i<lists.size(); i++) {
			lists.get(i).showAllData();
		}
		System.out.println("==전체정보가 출력되었습니다==");
	}
	
	
	/*
	개선된 for문을 이용한다. 인덱스를 사용할 필요가 없으므로 코드는
	훨씬 더 간결해진다. 컬렉션에 저장된 객체의 갯수만큼 알아서 반복한다.
	 */
	public void showSimpleData() { // 간략정보출력용 메서드
		for(Friend fr : lists) {
			fr.showBasicInfo();
		}
		System.out.println("==간략정보가 출력되었습니다==");
	}
	
	//주소록 검색
	public void searchInfo() {
		
		boolean isFind = false; //검색한 정보가 있는지 확인하기 위한 변수
		Scanner scan = new Scanner(System.in);
		System.out.print("검색할 이름을 입력하세요:");
		String SearchName = scan.nextLine();
		
		/*
		이터레이터 사용방법
		1. 컬렉션의 참조변수를 기반으로 이터레이터 객체를 생성한다.
		2. hasNext()로 출력할 요소가 있는지 검사한다.
		3. true를 반환하면 next()로 반환한다.
		단, while문 내에서 next()를 두번 호출하면 다음..다음.. 요소가 
		반환되므로 주의해야 한다.
		 */
		Iterator<Friend> itr = lists.iterator();
		while(itr.hasNext()) {
			
			Friend fr = itr.next();
			if(SearchName.compareTo(fr.name)==0) {
				//일치하는 이름이 있으면 정보를 출력한다.
				fr.showAllData();
				
				System.out.println("**귀하가 요청하는 정보를 찾았습니다.**");
				isFind = true; // 찾는 정보가 있다면 true로 변경
			}
		}
		
		if(isFind==false) {
			System.out.println("***찾는 정보가 없습니다.***");
		}
	}
	
	//주소록 삭제
	public void deleteInfo() {
		Scanner scan = new Scanner(System.in);
		System.out.print("삭제할 이름을 입력하세요:");
		String deleteName = scan.nextLine();
		
		
		int deleteIndex = -1;
		//저장된 데이터만큼 반복
		for(Friend fr : lists) {
			//삭제할 이름이 있는지 검색
			if(deleteName.compareTo(fr.name)==0) {
				lists.remove(fr);
				deleteIndex = 1; //단순히 삭제여부를 확인하기 위해 변경
				break;
			}
		}
		
		if(deleteIndex==-1) {
			
			System.out.println("==삭제된 데이터가 없습니다==");
		}
		else {
			
			System.out.println("==데이터("+deleteIndex+"번)가 삭제되었습니다.==");
		}
	}
}

public class Ex06MyFriendInfoBook
{
	public static void menuShow() {
		System.out.println("##### 메뉴를 입력하세요 #####");
		System.out.print("1.고딩친구입력 ");
		System.out.println("2.대딩친구입력");
		System.out.print("3.전체정보입력 ");
		System.out.println("4.간략정보입력");
		System.out.print("5.검색 ");
		System.out.print("6.삭제 ");
		System.out.println("7.프로그램종료");
		System.out.print("메뉴선택>>>");
	}
	
	
	
	public static void main(String[] args)
	{
		
		FriendIndoHandler handler = new FriendIndoHandler(100);
		
		Scanner scan = new Scanner(System.in);
		//무한루프 조건으로 특정 입력에만 종료할 수 있는 구조를 만들어준다.
		while(true) {
			menuShow();
			
			int choice = scan.nextInt();
			switch(choice) {
			case 1: case 2:
				//System.out.println("고딩/대딩 친구 입력");
				handler.addFriend(choice);
				break; // break문을 만나면 whitch문을 탈출한다.
			case 3:
				//System.out.println("전체정보출력");
				handler.showAllData();
				break;
			case 4:
				//System.out.println("간략정보출력");
				handler.showSimpleData();
				break;
			case 5:
				//System.out.println("검색");
				handler.searchInfo();
				break;
			case 6:
				//System.out.println("삭제");
				handler.deleteInfo();
				break;
			case 7:
				System.out.println("프로그램종료");
				return; // main 메서드의 종료이므로 프로그램 자체의 종료로 이어진다.
				
			}////switch 끝
		}//// while 끝	
	}////main 끝
}//// class 끝
