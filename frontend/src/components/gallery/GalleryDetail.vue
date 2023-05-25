<template>
    <div class="container-fluid p-0">
        <!-- title section -->
        <div class="header d-flex flex-column h-100 mt-5">
            <div class="board-content d-flex align-items-center">
                <div class="d-flex flex-grow-2 w-auto" style="padding: 0px 300px">
                    <b-button class="button mr-2" @click="moveHotPlaceList">목록</b-button>
                    <b-button v-if="userInfo && (userInfo.userId == hotplaceInfo.userId)" class="button mr-2" @click="moveHotPlaceModify">핫플레이스 수정</b-button>
                    <b-button v-if="userInfo && (userInfo.userId == hotplaceInfo.userId)" class="button btn-danger mr-2" @click="deleteHotPlace"> 삭제</b-button>
                </div>
            </div>
        </div>

        <div class="title-section d-flex flex-column">

            <div class="title d-flex mt-3 font-weight-bold"> {{ hotplaceInfo.title }}</div>

            <div class="d-flex align-items-center mt-1">
                <div class="mr-3">
                    <b-badge v-if="hotplaceInfo.hashTag" class="p-2 mr-3" variant="secondary"> {{hotplaceInfo.hashTag}}</b-badge>
                    <b-button class="p-0" variant="none" @click="toggleLike">
                        <b-icon v-if="isFill" class="mr-1 font-weight-bold font-red" icon="heart-fill" aria-hidden="true"></b-icon>
                        <b-icon v-else class="mr-1 font-weight-bold font-red" icon="heart" aria-hidden="true"></b-icon>
                    </b-button>
                    <span class="like font-weight-bold pt-1">{{ hotplaceInfo.like }} </span>
                </div>
                
                <p class="writer m-0">작성자 :
                    <span class="name font-weight-bold pt-1">
                        {{ hotplaceInfo.userName }}
                    </span>
                </p>
                <p class="time my-0 ml-3 font-italic">
                    {{ hotplaceInfo.createdAt }}
                </p>
            </div>

            <div class="d-flex justify-content-between mt-4">
                <KaKaoMap :mapCss="mapStyle"/>
                <b-carousel :interval="0" controls indicators background="#ababab" class="rounded w-50">
                    <b-carousel-slide v-for="image in hotplaceInfo.images" :key="image" >
                        <template #img>
                            <img
                                class="d-block img-fluid w-100"
                                :src="`${rootPath}/image/`+image"
                                alt="image slot">
                        </template>
                    </b-carousel-slide>
                </b-carousel>
            </div>
            <!--  Body -->
            <div class="d-flex flex-column mt-4 pr-3">
                <div class="info d-flex font-weight-bold">
                    Hot Place information
                </div>
                <div class="location d-flex font-weight-bold mt-4">
                    Location
                </div>
                <div class="d-flex content mt-1">
                        {{ hotplaceInfo.location }}
                </div>
                <div class="desc d-flex font-weight-bold mt-4">
                    상세 설명
                </div>
                <div class="d-flex justify-content-start content mt-1">
                    <pre class="text-left content-text">{{ hotplaceInfo.content }}
                    </pre>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import KaKaoMap from "@/components/map/KakaoMap.vue"
import { setMarker } from "@/util/daumPostUtil"
import { getHotPlace, deleteHotPlace, isUserLikeHotplace, toggleHotPlaceLike } from "@/api/hotplace" 
import { mapState } from "vuex"; 

export default {
    name: 'GalleryDetail',
    components: {
        KaKaoMap
    },
    computed: {
        ...mapState("userStore", ["userInfo"]),
        rootPath: function () { 
            return process.env.VUE_APP_API;
        }
    },
    data() {
        return {
            hotplaceInfo: {
                hotPlaceId: -1,
                userId: -1,
                title: '',
                like: 0,
                userName: "",
                createdAt: "",
                location: "",
                content: "",
                hashTag : "",
                longitude : 0, 
                latitude: 0,
            },
            isFill : false,
            mapStyle : {
                'display': 'table',
                'width': '49%',
                'height': '560px',
            },
        };
    },
    setup() { },
    created() {
        getHotPlace(this.$route.params.no, (response) => {
            this.hotplaceInfo.userId = response.data.userId;
            this.hotplaceInfo.hotPlaceId = response.data.hotPlaceId;
            this.hotplaceInfo.title = response.data.title;
            this.hotplaceInfo.userName = response.data.userName;
            this.hotplaceInfo.content = response.data.content;
            this.hotplaceInfo.location = response.data.location;
            this.hotplaceInfo.images = response.data.images;
            this.hotplaceInfo.hashTag = response.data.hashTag;
            this.hotplaceInfo.latitude = response.data.latitude;
            this.hotplaceInfo.longitude = response.data.longitude;
            this.hotplaceInfo.like = response.data.likeCnt;
            setMarker(this.hotplaceInfo.latitude, this.hotplaceInfo.longitude);
        });

        isUserLikeHotplace(this.$route.params.no, (response)=>{
            this.isFill = response.data;
        })
     },
    methods: {
        moveHotPlaceList() {
            this.$router.push({name:"GalleryList"}).catch(()=>{});
        },
        moveHotPlaceModify() {
            this.$router.push({name:"GalleryModify", params:{'no': this.hotplaceInfo.hotPlaceId}}).catch(()=>{});
        },
        deleteHotPlace() {
            deleteHotPlace(this.hotplaceInfo.hotPlaceId, ()=>{
                alert("삭제에 성공했습니다.");
                this.$router.push({name:"GalleryList"}).catch(()=>{});
            });
        },
        toggleLike() {
            toggleHotPlaceLike(this.hotplaceInfo.hotPlaceId, (response)=>{
                if(response.status === 200) {
                    this.isFill = !this.isFill;
                    if (this.isFill) {
                        this.hotplaceInfo.like += 1;
                    } else { 
                        this.hotplaceInfo.like -= 1;
                    }
                }
            });
        }
    },
}
</script>

<style scoped>
.title-section {
    padding: 20px 300px 200px 300px;
    min-width: 1100px !important;
}
.title {
    font-size: 1.8rem;
}
.like {
    font-size: 1.2rem;
    text-decoration: underline;
}
.writer {
    font-size: 1.2rem;
}
.name {
    text-decoration: underline;
}
.time {
    font-size: 1.1rem;
}
img {
    overflow-x: hidden;
    height: 560px !important;
    object-fit: cover !important;
}
.info {
    font-size: 1.6rem;
}
.location .desc {
    font-size: 1.3rem;
}
.content-text {
    font: inherit;
    white-space: pre-wrap;
}
</style>
