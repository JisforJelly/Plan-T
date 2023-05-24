<template>
<div class="content-area d-flex">
    <!-- Sidebar Start -->
    <div class="sidebar bg-light border-right">
        <div class="sidebar-heading shadow">
            <div>
                <img class="image" src="@/assets/plane.png" />
                <span class="font-weight-bold ml-2"> 나만의 플랜 만들기</span>
            </div>
            <b-form-datepicker 
                v-model="editForm.startDate"
                class="datepicker mb-2"
                placeholder="출발일 선택"
                :date-format-options="{ year: 'numeric', month: 'short', day: '2-digit'}"
            ></b-form-datepicker>
            <b-input v-model="editForm.title" placeholder="플랜 제목"></b-input>
        </div>
        <!-- Drag and Drop Start -->
        <draggable v-model="lists">
            <transition-group>
                <PlanEditItem :item="item" v-for="(item, index) in lists" :key="index + 0" @deleteEditItem="deleteEditDto" @modifyEditItem="showModal"/>
            </transition-group>
        </draggable>
        <!-- Drag and Drop End -->
    </div>  
    <!-- Sidebar End -->

    <!-- Page Content Start -->
    <div class="map-container">
        <Tmap :attractions="attractions" :lists="lists" :detailPath="lists" @showAttrModal="showModal"></Tmap>
        <div class="search-save">
            <div class="d-flex justify-content-between w-100">
                <div class="d-flex search">
                    <div class="d-flex">
                        <b-form-select class="mr-2 w-auto border shadow" v-model="selectedSido" :options="sidoCode"></b-form-select>
                        <b-form-select class="mr-2 w-auto border shadow" v-model="selectedgugunCode" :options="gugunCode"></b-form-select>
                        <b-form-select class="mr-2 w-auto border shadow" v-model="selectedContentType" :options="contentType"></b-form-select>
                    </div>
                    <div class="d-flex">
                        <b-input-group class="border rounded shadow">
                            <b-form-input 
                            type="search"
                            placeholder="지역을 검색하세요"
                            v-model="searchText"
                            ></b-form-input>
                            <b-input-group-append>
                                <b-button class="button">
                                    <b-icon icon="search" @click="searchAttr"></b-icon>
                                </b-button>
                            </b-input-group-append>
                        </b-input-group>
                    </div>
                </div>
            
                <div class="d-flex search">
                    <b-button class="button save-button" @click="regist">저장하기</b-button>
                </div>
            </div>
        </div>
    </div>
    <!-- Page Content End -->
    <b-modal style="width: 120px" 
        ref="add-modal"
        centered 
        class="h-50 d-inline-block min-vw-100">
        <template #modal-title>
            <code> {{ currentModalContent.placeName }}</code>
        </template>

        <div class="d-flex flex-column w-100">
            <div>
                <b-img v-if="currentModalContent.imgPath" :src="currentModalContent.imgPath" fluid alt="Responsive image"></b-img>
                <img v-else class="image" src="@/assets/noimg.jpg" style="width:100%; height: auto;"/>
            </div>

            <div class="mt-2">
                <p class="font-weight-bold">{{ currentModalContent.location }} </p>
                <b-form-select v-model="selectedDay" :options="dayOptions"></b-form-select>
                <b-textarea class="mt-2"
                    placeholder="여행계획을 작성하세요."
                    v-model="newTimeLineContent">
                </b-textarea>
            </div>
        </div>

        <template #modal-footer>
            <button class="btn btn-sm m-1" @click="cancelModal">취소</button>
            <button v-if="isEditMode" class="btn btn-primary btn-sm m-1" @click="addTimeLine">수정하기</button>
            <button v-else class="btn btn-primary btn-sm m-1" @click="addTimeLine">추가하기</button>
        </template>
    </b-modal>
</div>
</template>

<script>
import draggable from 'vuedraggable'
import Tmap from '@/components/map/TMap.vue'
import PlanEditItem from "@/components/plan/PlanEditItem.vue"
import { getSido, getGugun, getContent } from "@/api/meta"
import { searchAttraction } from "@/api/public"
import { insertTripPlan, getTripPlan } from "@/api/tripPlan"

export default {
    name:'PlanRegist',
    components: {
        PlanEditItem,
        draggable,
        Tmap,
    },
    data() {
        return {
            selectedDay: 1,
            dayOptions: [
                { value: 1, text: '1박' },
                { value: 2, text: '2박' },
                { value: 3, text: '3박' },
                { value: 4, text: '4박' },
                { value: 5, text: '5박' },
                { value: 6, text: '6박' },
                { value: 7, text: '7박' },
                { value: 8, text: '8박' },
                { value: 9, text: '9박' },
            ],
            lists: [],
            selectedSido: null,
            selectedgugunCode: null,
            selectedContentType: null,
            searchText: "",
            sidoCode: {
            },
            gugunCode: [
                { value: null, text: '군/구', disabled: true },
            ],
            contentType: [
                { value: null, text: '관광지 유형', disabled: true },
            ],
            attractions: [],
            currentModalContent: {},
            newTimeLineContent: "",
            editForm: {
                title: "",
                startDate: "",
                timeLines: [],
            },
            isEditMode: false,
        };
    },
    watch: {
        selectedSido(value) { 
            this.gugunCode = [...this.sidoCode[value].gugun, { value: null, text: '군/구', disabled: true }];
            this.selectedgugunCode = null;
        }
    },
    created() {
        getSido((response) => { 
            this.sidoCode = {"default":{ value: null, text: '시/도', disabled: true }};
            response.data.forEach((sido) => { 
                this.sidoCode[sido.code] = {
                    value: sido.code,
                    text: sido.name,
                    gugun: [],
                }
            })
            getGugun((response) => {
                response.data.forEach((gugun) => { 
                    this.sidoCode[gugun.mappingCode].gugun.push({
                        value: gugun.code,
                        text: gugun.name
                    })
                })
            }); 
        });
        getContent((response) => { 
            this.contentType = response.data.map((content) => {
                return {
                    value: content.code,
                    text: content.name
                }
            });
            this.contentType.push({ value: null, text: '관광지 유형', disabled: true });
        });

        if(this.$route.params.no !== -1) {
            getTripPlan(this.$route.params.no, (response)=>{
                console.log(response);
                this.lists = response.data.destinations.map((attr)=>{
                    const start = Date.parse(attr.startDate);
                    const end = Date.parse(attr.endDate);
                    console.log(start, end, end-start);
                    return {
                        ...attr,
                        day : Math.floor(Math.abs((end-start) / (1000 * 60 * 60 * 24))),
                    }
                });
                this.editForm.title = response.data.title;
                this.editForm.startDate = response.data.destinations[0].startDate
            });
        }
    },
    mounted() {
    },
    unmounted() {},
    methods: {
        regist() { 
            if (this.editForm.startDate === "") { 
                alert("시작일을 선택하세요.");
                return;
            }
            if (this.editForm.title === "") { 
                alert("제목을 입력하세요.");
                return;
            }
            if (this.lists.length === 0) { 
                alert("여행지를 선택하세요.");
                return;
            }

            for (let i = 0; i < this.lists.length; ++i) { 
                this.lists[i]["order"] = i + 1;
            }

            this.editForm.timeLines = this.lists;
            insertTripPlan(this.editForm, () => { 
                alert("등록에 성공했습니다.");
                this.$router.push({name:"PlanView"}).catch(()=>{});
            })
        },
        searchAttr() { 
            searchAttraction(
                this.selectedSido,
                this.selectedgugunCode,
                this.selectedContentType,
                this.searchText,
                async (response) => {
                    const json = await response.json();
                    this.attractions = json.response.body.items.item.map((attraction) => {
                        return {
                            tripPlanTimeLineId: -1,
                            tripPlanId: -1,
                            order: -1,
                            placeName: attraction.title,
                            latitude: attraction.mapy,
                            longitude: attraction.mapx,
                            location: attraction.addr1,
                            content: "",
                            imgPath: attraction.firstimage,
                        };
                    });
                });
        },
        showModal(attr, isEdit) { 
            this.isEditMode = isEdit;
            if (isEdit) { 
                this.newTimeLineContent = attr.content;
            }
            this.currentModalContent = attr;
            this.$refs['add-modal'].show();
        },
        cancelModal() { 
            this.$refs['add-modal'].hide();
        },
        addTimeLine() { 
            const timeLine = {
                tripPlanTimeLineId: -1,
                tripPlanId: -1,
                order: -1,
                placeName: this.currentModalContent.placeName,
                latitude: this.currentModalContent.latitude,
                longitude: this.currentModalContent.longitude,
                location: this.currentModalContent.location,
                content: this.newTimeLineContent,
                imgPath: this.currentModalContent.imgPath,
                day: this.selectedDay,
            };

            if (this.isEditMode) {
                this.deleteEditDto(timeLine);
            } 
            const newList = [...this.lists, timeLine];
            this.lists = newList;
            this.$refs['add-modal'].hide();
            this.selectedDay = 1;
            this.newTimeLineContent = "";
        },
        deleteEditDto(attr) {
            for (let i = 0; i < this.lists.length; ++i) { 
                if (attr.placeName === this.lists[i].placeName) { 
                    const newList = [...this.lists];
                    newList.splice(i, 1);
                    this.lists = newList;
                    break;
                }
            }
        }

    },
};
</script>

<style scoped>
.image {
    width: 30px;
}
.content-area {
    height: 100vh;
}
.sidebar {
    padding-top: 10px;
    width: 320px;
}
.sidebar-heading {
    padding: 0.875rem 1.25rem;
    font-size: 1.2rem;
}
.list-item {
    height: 40px;
    line-height: 40px; /* 글자 vertical 중앙 정렬 */
    border: 1px solid #62aff6;
    margin: 10px 30px;
    border-radius: 5px;
    background-color: #62aff6;
    color: black;
    font-weight: bold;
}
.datepicker {
    margin-top: 10px;
    font-size: 1.0rem;
}
.map-container {
    float: center;
    width: calc(100% - 280px);
    overflow: hidden;
    position: relative;
    height: 100vh;
}
.image-map {
    position: relative;
}
.search-save {
    position: absolute; 
    display: flex;
    top: 0;
    left: 0;
    right: 0;
}
.search {
    float: left;
    padding: 10px;
    margin: 30px 0px 30px 10px;
}
.button {
    color:white;
    background-color: #62aff6;
    border: 1px solid #62aff6;
}
.save-button {
    margin-right: 50px;
}
</style>