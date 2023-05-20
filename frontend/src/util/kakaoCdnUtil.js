let map;
let positions;
let markerList;

export const initMap = ($mapContainer) => {
    const mapOption = {
        center: new window.kakao.maps.LatLng(37.500613, 127.036431), 
        level: 5,
    };
    
    map = new window.kakao.maps.Map($mapContainer, mapOption);
    map.addControl(new window.kakao.maps.MapTypeControl(), window.kakao.maps.ControlPosition.TOPRIGHT);
    map.addControl(new window.kakao.maps.ZoomControl(), window.kakao.maps.ControlPosition.RIGHT);
}

export const initPositions = (data) => {
	positions = [];
	data.forEach((area)=>{
		positions.push({
            title: area.title,
            latlng: new window.kakao.maps.LatLng(area.mapy, area.mapx),
			});
	});
}

export const displayMarker = () => {
	const imgResource = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	
	markerList = [];
	for(let i=0; i<positions.length; ++i) {
		const imageSize = new window.kakao.maps.Size(24, 35);
		const markerImage = new window.kakao.maps.MarkerImage(imgResource, imageSize);
		markerList.push(new window.kakao.maps.Marker({
                map: map,
                position: positions[i].latlng,
                title: positions[i].title,
                image: markerImage,
            })
        );
	}
	map.setCenter(positions[0].latlng);
}

export const moveCenter = (lat, lng) => {
    map.panTo(new window.kakao.maps.LatLng(lat, lng));   
}

export const makeFixedMarker = (lat, lng) => {
	const imgResource = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
	const imageSize = new window.kakao.maps.Size(53, 53);
	const imageOption = {
		offset : new window.kakao.maps.Point(27, 53)
	}; 
	
	const markerImage = new window.kakao.maps.MarkerImage(imgResource, imageSize, imageOption);
	const markerPosition = new window.kakao.maps.LatLng(lat, lng); 
	const marker = new window.kakao.maps.Marker({
		position : markerPosition,
		image : markerImage,
	});
	marker.setMap(map);
}
