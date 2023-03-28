console.log("main.js loaded.");

// 회원 정보 조회 비동기 통신(AJAX)

document.getElementById("select1").addEventListener("click", function() {

	const input = document.getElementById("in1");
	const div = document.getElementById("result1");

	// AJAX 코드 작성(jQuery 방식)
	$.ajax({
		url: "member/selectOne",
		data: { "memberEmail": input.value },
		type: "post",
		dataType: "JSON", // dataType: 응답 데이터 형식을 지정
		// -> "JSON"으로 지정 시 자동으로 JS 객체로 변환
		success: function(member) {
			console.log(member);

			// 1) div에 작성된 내용 모두 삭제
			div.innerHTML = "";

			if (member != null) { // 회원 정보 존재 O

				// 2) ul 요소 생성
				const ul = document.createElement("ul");

				// 3) li 요소 생성 * 5 + 내용 추가
				const li1 = document.createElement("li");
				li1.innerText = "이메일 : " + member.memberEmail;

				const li2 = document.createElement("li");
				li2.innerText = "닉네임 : " + member.memberNickname;

				const li3 = document.createElement("li");
				li3.innerText = "전화번호 : " + member.memberTel;

				const li4 = document.createElement("li");
				li4.innerText = "주소 : " + member.memberAddress;

				const li5 = document.createElement("li");
				li5.innerText = "가입일 : " + member.enrollDate;

				// 4) ul에 li를 순서대로 추가
				ul.append(li1, li2, li3, li4, li5);

				// 5) div에 ul 추가
				div.append(ul);

			} else { // 회원 정보 존재 X

				// 1) h4 요소 생성
				const h4 = document.createElement("h4");

				// 2) 내용 추가
				h4.innerText = "일치하는 회원이 없습니다";

				// 3) 색 추가
				h4.style.color = "red";

				// 4) div에 추가
				div.append(h4);
			}
		},
		error: function(request) {
			console.log("AJAX 에러 발생")
			console.log("상태코드 : " + request.status) // 404, 500
		}


	})

});



console.log("main2.js loaded.");
function selectAll() {
		console.log("selectAll");
		$.ajax({
			url: "member/selectAll",
			type: "get",
			dataType: "JSON", // dataType: 응답 데이터 형식을 지정
			// -> "JSON"으로 지정 시 자동으로 JS 객체로 변환

			success: function(mbList) {
				console.log("mbList");

				const mbtl = document.getElementById("mbList");

				mbtl.innerHTML = "";

				// 2) list를 for문을 이용해서 반복 접근
				for (let i = 0; i < mbList.length; i++) {
					// 3) tr 요소 생성
					const tr = document.createElement("tr");

					// 4) td 요소 생성 + 내용추가 * 3
					const td1 = document.createElement("td");
					td1.innerText = mbList[i].memberNo;

					const td2 = document.createElement("td");
					td2.innerText = mbList[i].memberEmail;

					const td3 = document.createElement("td");
					td3.innerText = mbList[i].memberNickname;

					// 5) tr요소에 td요소 3개 추가
					tr.append(td1, td2, td3);

					// 6) mbList에 tr 추가
					mbtl.append(tr);

				}
			},
			error: function(request) {
				console.log("AJAX 에러 발생")
				console.log("상태코드 : " + request.status) // 404, 500
			}
		})
}

(function(){
   console.log("start")
   selectAll();
   window.setInterval(selectAll,5000)
})();
