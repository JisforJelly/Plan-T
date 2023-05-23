let $mapContainer;
let map, marker;
let coordinates = null;
const geoCoder = new window.daum.maps.services.Geocoder();

export const setDaumMap = ($element) => {
    $mapContainer = $element;
    const mapOption = {
        center: new window.daum.maps.LatLng(37.537187, 127.005476),
        level: 5 // 지도의 확대 레벨
    };

    map = new window.daum.maps.Map($mapContainer, mapOption);
    marker = new window.daum.maps.Marker({
        map: map
    });
}

export const openDaumPost = (callback) => {
    new window.daum.Postcode({
        oncomplete: function(data) {
            geoCoder.addressSearch(data.address, function(results, status) {
                if (status === window.daum.maps.services.Status.OK) {
                    const result = results[0];
                    coordinates = {
                        latitude: result.y,
                        longitude: result.x
                    }

                    const coords = new window.daum.maps.LatLng(result.y, result.x);
                    $mapContainer.style.display = "block";
                    map.relayout();
                    map.setCenter(coords);
                    marker.setPosition(coords)
                    
                    callback(data.address);
                }
            });
        }
    }).open();
}

export const setMarker = (latitude, longitude) => {
    const coords = new window.daum.maps.LatLng(latitude, longitude);
    map.setCenter(coords); 
    marker.setPosition(coords)
    console.log(coords, map)
}

export const getLatLng = () => {
    return coordinates;
};
