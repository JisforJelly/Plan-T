<template>
  <div class="container-fluid p-0">
    <!-- Header -->
    <div class="header d-flex flex-column h-100">
      <div>
        <h2 class="font-weight-bold mb-0 w-auto">핫플레이스 수정</h2>
      </div>
      <div class="board-content d-flex align-items-center justify-content-between">
        <div class="d-flex flex-grow-2 w-auto">
          <b-button class="button mr-2" @click="moveGalleryList">목록</b-button>
          <b-button class="button" @click="modifyHotPlace">핫플레이스 수정</b-button>
        </div>
      </div>
    </div>

    <!-- Board Section -->
    <div class="board-section">
      <!-- 제목 -->
      <b-form-input
        size="lg"
        placeholder="제목"
        class="mb-2"
        v-model="hotplaceForm.title"
      ></b-form-input>
      <!-- 해시태그 -->
      <b-form-input
        size="lg"
        placeholder="#해시태그"
        class="mb-2"
        v-model="hotplaceForm.hashTag"
      ></b-form-input>

      <div class="d-flex justify-content-between mb-2">
        <KaKaoMap :mapCss="mapStyle" />
        <b-carousel :interval="0" controls indicators background="#ababab" class="rounded w-50">
          <b-carousel-slide v-for="image in hotplaceForm.imgPath" :key="image">
            <template #img>
              <img class="d-block img-fluid w-100" :src="'http://localhost:8080/image/'+image" alt="image slot" />
            </template>
          </b-carousel-slide>
        </b-carousel>
      </div>

      <!-- 주소 -->
      <b-input-group>
        <b-form-input
          size="lg"
          v-model="hotplaceForm.location"
          disabledtype="text"
          placeholder="주소"
          readonly
        ></b-form-input>
        <b-input-group-append>
          <b-button class="address-button" size="lg" @click="openAddressSearch">주소 검색</b-button>
        </b-input-group-append>
      </b-input-group>

      <b-form-select
        class="mt-2"
        size="lg"
        v-model="hotplaceForm.contentType"
        :options="options"
      ></b-form-select>

      <!-- 상세 설명 -->
      <b-form-textarea
        class="mt-2"
        placeholder="HotPlace 설명을 작성해주세요."
        rows="3"
        max-rows="8"
        v-model="hotplaceForm.content"
      ></b-form-textarea>
    </div>
  </div>
</template>
<script>
import { getContent } from "@/api/meta";
import { updateHotPlace } from "@/api/hotplace";
import KaKaoMap from "@/components/map/KakaoMap.vue";
import { openDaumPost, getLatLng, setMarker } from "@/util/daumPostUtil";
import { getHotPlace } from "@/api/hotplace"

export default {
  name: "GalleryModify",
  components: {
    KaKaoMap,
  },
  data() {
    return {
      mapStyle: {
        display: "table",
        width: "49%",
        height: "300px",
      },
      hotplaceForm: {
        hotPlaceId: -1,
        title: "",
        content: "",
        location: "",
        latitude: "",
        longitude: "",
        hashTag: "",
        contentType: null,
        imgPath: [],
      },
      options: [],
      defaultOptions: {
        value: null,
        text: "장소 타입을 선택하세요.",
        disabled: true,
      },
    };
  },
  created() {
    getContent((response) => {
      if (response.data) {
        const option = response.data.map((code) => {
          return {
            value: code.name,
            text: code.name,
          };
        });

        this.options = [this.defaultOptions, ...option];
      }
    }),
    getHotPlace(this.$route.params.no, (response)=>{
        this.hotplaceForm.hotPlaceId = response.data.hotPlaceId;
        this.hotplaceForm.title = response.data.title;
        this.hotplaceForm.content = response.data.content;
        this.hotplaceForm.location = response.data.location;
        this.hotplaceForm.imgPath = response.data.images;
        this.hotplaceForm.hashTag = response.data.hashTag;
        this.hotplaceForm.latitude = response.data.latitude;
        this.hotplaceForm.longitude = response.data.longitude;
        this.hotplaceForm.contentType = response.data.contentType;
        setMarker(response.data.latitude, response.data.longitude);
    });
  },
  methods: {
    moveGalleryList() {
      this.$router.push({ name: "GalleryList" }).catch(() => {});
    },
    modifyHotPlace() {
      if (!this.hotplaceForm.location) {
        alert("주소를 선택해주세요.");
        return;
      }

      updateHotPlace(this.hotplaceForm, () => {
        this.$router.push({ name: "GalleryList" }).catch(() => {});
      }, ()=>{
        alert("수정에 실패하였습니다. 관리자에게 문의하세요.");
      });
    },
    openAddressSearch() {
      openDaumPost((address) => {
        this.hotplaceForm.location = address;
        const loc = getLatLng();
        this.hotplaceForm.latitude = loc.latitude;
        this.hotplaceForm.longitude = loc.longitude;
      });
    },
  },
};
</script>

<style scoped>
.header {
  padding: 20px 300px;
  margin-top: 60px;
  min-width: 1100px !important;
}
.board-section {
  padding: 20px 300px 1000px 300px;
  overflow: hidden;
  height: 800px;
  min-width: 1100px !important;
}
.board-content {
  margin-top: 60px;
}
.upload {
  margin-top: 10px;
  text-align: left;
}
.address-button {
  color: #495057;
  background-color: #e9ecef;
  border: 1px solid #ced4da;
}
.address-button:hover {
  color: #495057;
  background-color: #ced4da;
  border: 1px solid #ced4da;
}
img {
  overflow-x: hidden;
  height: 300px !important;
  object-fit: cover !important;
}
</style>
