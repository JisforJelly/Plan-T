const url =
  "https://apis.data.go.kr/B551011/KorService1/searchKeyword1?serviceKey=ius%2FScb1AYmLrpM702MaMchCJfFc6mFjtKFVwh1mob1X99jL7goonBAIMoSkL5IAhiW0ZsqTo1dtz2Ls0OnStg%3D%3D&numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A";

async function searchAttraction(sido, gungu, contentType, keyword, success) {
  let searchUrl = url;

  if (parseInt(sido)) searchUrl += `&areaCode=${sido}`;
  if (parseInt(gungu)) searchUrl += `&sigunguCode=${gungu}`;
  if (parseInt(contentType)) searchUrl += `&contentTypeId=${contentType}`;
  if (!keyword) {
    alert("검색하고자하는 여행지를 입력하세요.");
    return;
  } else searchUrl += `&keyword=${keyword}`;

  fetch(searchUrl)
    .then(success)
    .catch(() => {});
}

export { searchAttraction };
