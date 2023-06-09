
// 회원 프로필 이미지 변경()
const inputImage = document.getElementById("input-image");

if(inputImage != null) { // inputImage 요소가 화면에 존재할때
    
    // input type="file" 요소는 파일이 선택될 때 change 이벤트 발생

    inputImage.addEventListener("change", function() {

        // 파일 목록에서 첫번째 파일 객체를 선택
        // files : input type="file"만 사용 가능한 속성으로
        //          선택된 파일 목록(배열)을 반환

        // this : 이벤트가 발생한 요소 (input type ="file")

        if(this.files[0] != undefined) { // 파일이 선택 되었을때

            const reader = new FileReader();
            // 자바스크립트의 FileReader
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체
            
            reader.readAsDataURL(this.files[0]);
            // FileReader.readAsDataURL(파일)
            // - 지정된 파일의 내용을 읽기 시작함
            // - 읽어오는게 완료되면 result 속성에 data에
            // 읽어온 파일의 위치를 나타내는 URL이 포함된다.
            // -> 해당 URL을 이용해서 브라우저에 이미지를 볼 수 있다.

            reader.onload = function(e) {
                // e : 이벤트 발생 객체
                // e.target : 이벤트가 발생한 요소 -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                // 프로필 이미지의 src 속성의 값을 FileReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");
                
                profileImage.setAttribute("src", e.target.result);
                // -> setAttribute() 호출 시 중복되는 속성이 존재하면 덮어쓰기

                document.getElementById("delete").value = 0;
                // 새로운 이미지가 선택되었기 때문에 1 -> 0 (안눌러짐 상태)로 변경

            }
        }

    });

}


// 프로필 이미지 옆 X버튼 클릭 시
document.getElementById("delete-image").addEventListener("click", function() {
    // 0 : 안눌러짐
    // 1 : 눌러짐

    const del = document.getElementById("delete");

    if(del.value ==0) { // 눌러지지 않은 경우

        // 1) 프로필 이미지를 기본 이미지로 변경
        document.getElementById("profile-image").setAttribute("src", contextPath + "/resources/images/user.png");

        // 2) input type = "file"에 저장된 값(value) "" 대입
        document.getElementById("input-image").value = "";

        del.value = 1;

    }

});

// 이미지 선택 확인
function profileValidate() {
    const inputImage = document.getElementById("input-image");

    const del = document.getElementById("delete"); // hidden 타입

    if(inputImage.value == "" && del.value ==0) {
        // 파일선택X        /     X 를 누르지도 않았다
        // -> 아무것도 안하고 변경버튼만 클릭한 경우

        alert("이미지를 선택한 후 변경 버튼을 클릭해주세요");
        return false;
    }

    return true;
}


// 닉네임 수정하기
const memberNickname = document.getElementById("memberNickname");
const infoNicknameMessage = document.getElementById("infoNicknameMessage"); 

memberNickname.addEventListener("input", function() {
    console.log("닉네임 변경중...");

    // 입력이 되지 않은 경우
    if(memberNickname.value.length == 0) {
        infoNicknameMessage.innerText = "한글 2~10글자 사이로 작성해주세요.";
        infoNicknameMessage.classList.remove("confirm", "error");
        memberNickname.focus();
        return false;
    }

    // 입력이 된 경우
    const regExp = /^[a-z0-9가-힣]{2,10}/;
    if( regExp.test(memberNickname.value) ) { // 유효한 경우

        $.ajax({
            url : "nicknameDupCheck",
            data: {"memberNickname" : memberNickname.value},
            
            success : function(result) {
               
                console.log(result);
                
                if(result == 1) { // 중복 O
                    infoNicknameMessage.innerText = "이미 사용중인 닉네임 입니다.";
                    infoNicknameMessage.classList.add("error");
                    infoNicknameMessage.classList.remove("confirm");  
                } else {  // 중복 X
                    infoNicknameMessage.innerText = "사용 가능한 닉네임 입니다.";
                    infoNicknameMessage.classList.remove("error");
                    infoNicknameMessage.classList.add("confirm");
                }
            },
            error : function() {
                // 비동기 통신(ajax)중 오류가 발생한 경우
                console.log("에러 발생");
            }
        });
    } else {
        infoNicknameMessage.innerText = "닉네임 형식이 유효하지 않습니다.";
        infoNicknameMessage.classList.add("error");
        infoNicknameMessage.classList.remove("confirm");

        
    }
});

// 전화번호 수정
const memberTel = document.getElementById("memberTel");
const infotelMessage = document.getElementById("infotelMessage");

memberTel.addEventListener("input", function() {

     console.log("dwdwd")

    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;
    if(regExp.test(memberTel.value)) {
        infotelMessage.innerText = "등록 가능한 번호 입니다" 
        infotelMessage.classList.add("confirm");
        infotelMessage.classList.remove("error");

    } else {
        infotelMessage.innerText = "등록 불가능한 번호 입니다" 
        infotelMessage.classList.add("error"); 
        infotelMessage.classList.remove("confirm");

    }
});

// 수정하기 버튼 
function infoValidate() {

    // checkObj에 있는 모든 속성을 반복 접근하여
    // false가 하나라도 있는 경우에는 form태그 기본 이벤트 제거

    let str;

    for (let key in myPageObj) {

        // 현재 접근 중인 key의 value가 false인 경우
        if(!myPageObj[key]) {

            switch(key) {
                
                case "memberNickname": str="닉네임이"; break;
                case "memberTel": str="전화번호가"; break;
            }

            str += " 유효하지 않습니다.";

            alert(str);

            document.getElementById(key).focus();

            return false; // form 태그 기본 이벤트 제거
        }


    }
    
    alert("수정완료");

    return true; // form 태그 기본 이벤트 수행
}

// 경고 출력 + 포커스 + false 반환  함수
function printAlert(el, message){ // 매개변수 el은 요소
    alert(message);
    el.focus();
    return false;
}



// 비밀번호 변경 제출 시 유효성 검사
function changePwValidate(){

    // 비밀번호 변경 관련 input 요소 얻어오기
    const currentPw = document.getElementsByName("currentPw")[0];
    
    //const currentPw = document.getElementById("currentPw");

    const newPw = document.getElementsByName("newPw")[0];
    const newPwConfirm = document.getElementsByName("newPwConfirm")[0];

    // 비밀번호 정규표현식
    const regEx = /^[\w!@#_-]{6,30}$/;

    // 현재 비밀번호 미작성
    if(currentPw.value.trim().length == 0){
        /*alert("현재 비밀번호를 입력해주세요.");
        currentPw.focus();
        return false;*/

        return printAlert(currentPw, "현재 비밀번호를 입력해주세요.");
    }

    // 새 비밀번호
    // 미작성
    if(newPw.value.trim().length == 0){
        alert("새 비밀번호를 입력해주세요.");
        newPw.focus();
        return false;
    }

    // 유효하지 않은 경우
    if(!regEx.test(newPw.value)){
        alert("영어, 숫자, 특수문자(!,@,#,-,_) 6~30 글자 사이로 작성해주세요.");
        newPw.focus();
        return false;
    }


    // 새 비밀번호 확인
    // 미작성
    if(newPwConfirm.value.trim().length == 0){
        return printAlert(newPwConfirm, "새 비밀번호 확인을 입력해주세요.");
    }


    // 새 비밀번호 != 새 비밀번호 확인
    if(newPw.value != newPwConfirm.value){
        return printAlert(newPwConfirm, "새 비밀번호가 일치하지 않습니다.");
    }

    return true; // 위 조건을 모두 수행하지 않은 경우 true 반환
}

// 회원탈퇴 유효성 검사
function secessionValidate() {

    const memberPw = document.getElementById("memberPw");
    const agree = document.getElementById("agree");

    // 비밀번호 미작성
    if(memberPw.value.length==0) {
        alert("비밀번호를 기입해야 탈퇴가 가능합니다")
        memberPw.focus();
        return false;
    }
    // 약관동의체크 어그리
    if(!agree.checked){
        alert("약관동의를 체크해주셔야 탈퇴 가능합니다")
        agree.focus();
        return false;
    }
    // 정말 탈퇴할지 확인
    if( !confirm("정말 탈퇴 하시겠습니까?") ){ //  취소를 누른 경우
        return false;
    }


    return true;
}