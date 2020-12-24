/**
 파일 업로드에 관련하여 공통으로 사용할 javaScript함수들을 따로 모아놓은 것들입니다.
 <script type="text/javascript" src="${path}/include/js/common.js"></script>
		를 view.jsp에 추가해주세요
 */
//이미지 파일 여부 판단
function checkImageType(fileName){
    var pattern = RegExp(/^.+\.(gif|png|jpg|jpeg)$/i);
    
    return pattern.test(fileName);
}

//업로드 파일 정보
function getFileInfo(fullName) {
    var fileName, imgsrc, getLink, fileLink;
    //이미지 파일일 경우
    if(checkImageType(fullName))
    {
    	console.log("이미지만 실행되야함");
        //이미지 파일 경로(썸네일)
        imgsrc = "/upload/displayFile?fileName="+fullName;
        console.log(imgsrc);

        //업로드 파일명
        fileLink = fullName.substr(14);
        console.log(fileLink);

        //날짜별 디렉토리 추출
        var front = fullName.substr(0,12);
        console.log(front);

        // s_를 제거한 업로드이미지 파일명
        var end = fullName.substr(14);
        console.log(end);

        // 원본이미지 파일 디렉토리
        getLink = "/upload/displayFile?fileName="+front+end;
        console.log(getLink);
    // 이미지 파일이 아닐경우
    } else {
        // UUID를 제외한 원본파일명
        fileLink = fullName.substr(12);
        console.log(fileLink);

        // 일반파일디렉토리
        getLink = "/upload/displayFile?fileName="+fullName;
        console.log(getLink);
        }

    // 목록에 출력할 원본파일명
    fileName = fileLink.substr(fileLink.indexOf("_")+1);
    console.log(fileName);
    // { 변수:값 } json 객체 리턴
    return {fileName:fileName, imgsrc:imgsrc, getLink:getLink, fullName:fullName};
}

            
        