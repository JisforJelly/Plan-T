<template>
    <div :class="[isFixed ? 'fixed-top' : 'sticky-top', 'shadow', 'py-2']" style="background-color: white;">
        <header>
            <div class="d-flex navbar-static-height">
                <div class="d-flex h-100 align-items-center">
                    <div @click="changeMenuByAsset()" style="cursor: pointer;">
                        <img class="logo" src="@/assets/EnjoyTrip-small.png" style="height: 80px;"/>
                    </div>
                </div>
                <div class="d-flex flex-grow-1 h-100 ml-2 align-items-center p-5">
                    <b-dropdown id="dropdown-header" :text="menu.text" class="m-2" variant="none" size="lg" dropright
                        toggle-class="custom-font-style">
                        <b-dropdown-header id="dropdown-header-label">
                            원하는 메뉴를 선택하세요.
                        </b-dropdown-header>
                        <div v-for="menu in menus" :key="menu.mkey">
                            <b-dropdown-item-button aria-describedby="dropdown-header-label" @click="changeMenu"
                                v-bind:value="menu.value">
                                {{ menu.text }}
                            </b-dropdown-item-button>
                        </div>
                    </b-dropdown>
                </div>

                <div class="d-flex align-items-center">
                    <div class="mr-2" v-if="menu.value == 0">
                        <router-link class="btn btn-light rounded-pill text-truncate simple-text-style"
                            :to="{ name: 'GalleryRegist' }">
                            당신의 <span class="font-red">Hot</span>한 공간을 공유하세요
                        </router-link>
                    </div>

                    <div class="mr-2" v-if="menu.value == 1">
                        <router-link class="btn btn-light rounded-pill text-truncate simple-text-style"
                            :to="{ name: 'PlanRegist', params:{no:-1} }">
                            당신의 <span class="font-red">여행</span>을 계획하세요
                        </router-link>
                    </div>

                    <b-dropdown v-if="!isLogin" class="m-2" variant="outline-secondary" no-caret toggle-class="round-drop-down"
                        offset="-60">
                        <!-- dropdown -->
                        <template #button-content>
                            <div size="sm" class="d-flex align-items-center">
                                <b-icon-list class="mx-1" />
                                <b-avatar class="ml-1"></b-avatar>
                            </div>
                        </template>
                        <!-- select option -->
                        <b-dropdown-item-button @click="beforeLogin" v-for="item in beforeLoginUserDropDown"
                            :key="item.mkey" aria-describedby="dropdown-header-label" v-bind:value="item.value">
                            {{ item.text }}
                        </b-dropdown-item-button>
                    </b-dropdown>

                    <b-dropdown v-else class="m-2" variant="outline-secondary" no-caret toggle-class="round-drop-down"
                        offset="-60">
                        <!-- dropdown -->
                        <template #button-content>
                            <div size="sm" class="d-flex align-items-center">
                                <b-icon-list class="mx-1" />
                                <b-avatar class="ml-1" :src="avatarSrc"></b-avatar>
                            </div>
                        </template>
                        <!-- select option  TODO Log out & my page-->
                        <b-dropdown-item-button v-for="item in afterLoginUserDropDown" @click="userAction(item.value)"
                            :key="item.mkey" aria-describedby="dropdown-header-label" v-bind:value="item.value">
                            {{ item.text }}
                        </b-dropdown-item-button>
                    </b-dropdown>
                </div>
            </div>
        </header>

        <!-- User Modal -->
        <b-modal id="userModal" header-class="d-none" body-class="userModalBody" footer-class="border-0 userModalFooter"
            @ok="confirmModal">
            <b-tabs class="w-100" v-model="tabIndex">
                <b-tab title="로그인" active>
                    <b-form class="mt-3">
                        <b-form-group>
                            <b-form-input class="mb-3" placeholder="아이디를 입력하세요." type="text"
                                v-model="signInForm.loginId"></b-form-input>
                            <b-form-input placeholder="비밀번호를 입력하세요." type="password"
                                v-model="signInForm.password"></b-form-input>
                        </b-form-group>
                    </b-form>
                </b-tab>
                <b-tab title="회원가입">
                    <b-form-group class="mt-3">
                        <b-form-input class="mb-3" placeholder="별명을 입력하세요." type="text"
                            v-model="signUpForm.name"></b-form-input>
                        <b-form-input class="mb-3" placeholder="이메일을 입력하세요." type="email"
                            v-model="signUpForm.email"></b-form-input>
                        <b-form-input class="mb-3" placeholder="아이디를 입력하세요." type="text"
                            v-model="signUpForm.loginId"></b-form-input>
                        <b-form-input class="mb-3" placeholder="비밀번호를 입력하세요." type="password"
                            v-model="signUpForm.password"></b-form-input>
                        <b-form-input placeholder="비밀번호를 확인하세요." type="password"></b-form-input>
                    </b-form-group>
                </b-tab>
            </b-tabs>
        </b-modal>

        <!-- find id modal -->
        <b-modal id="findIdModal" header-class="d-none" body-class="userModalBody" footer-class="border-0 userModalFooter"
            @ok="confirmAuthModal">
            <b-tabs class="w-100" v-model="findAuthIndex">
                <b-tab title="아이디 찾기" active>
                    <b-form class="mt-3">
                        <b-form-group>
                            <b-form-input class="mb-3" placeholder="가입 시 입력하신 이메일을 입력하세요." type="text"
                                v-model="findIdEmail"></b-form-input>
                        </b-form-group>
                    </b-form>
                </b-tab>
                <b-tab title="비밀번호 찾기">
                    <b-form-group class="mt-3">
                        <b-form-input class="mb-3" placeholder="아이디를 입력하세요" type="text"
                            v-model="findPasswordForm.loginId"></b-form-input>
                        <b-form-input class="mb-3" placeholder="이메일을 입력하세요." type="email"
                            v-model="findPasswordForm.email"></b-form-input>
                    </b-form-group>
                </b-tab>
            </b-tabs>
        </b-modal>
    </div>
</template>
<script>
import { signUp, sendFindIdEmail, sendFindPassword } from "@/api/auth"
import { mapActions, mapMutations, mapState } from "vuex";

export default {
    name: 'TheNavBar',
    components: {
    },
    data() {
        return {
            avatarSrc: "",
            tabIndex: 0,
            findAuthIndex: 0,
            menus: [
                {
                    mkey: "m-1",
                    value: 0,
                    text: "Hot-Place",
                    routeName: "GalleryList",
                },
                {
                    mkey: "m-2",
                    value: 1,
                    text: "Trip Plan",
                    routeName: "PlanView",
                },
                {
                    mkey: "m-3",
                    value: 2,
                    text: "자유 게시판",
                    routeName: "FreeBoard",
                }
            ],
            beforeLoginUserDropDown: [
                {
                    mkey: "blu-1",
                    value: 0,
                    text: "회원 가입",
                },
                {
                    mkey: "blu-2",
                    value: 1,
                    text: "로그인",
                },
                {
                    mkey: "blu-3",
                    value: 2,
                    text: "아이디 찾기",
                },
                {
                    mkey: "blu-4",
                    value: 3,
                    text: "비밀번호 찾기",
                },
            ],
            afterLoginUserDropDown: [
                {
                    mkey: "afu-1",
                    value: 0,
                    text: "로그아웃",
                },
                {
                    mkey: "afu-2",
                    value: 1,
                    text: "마이 페이지",
                },
            ],
            signInForm: {
                loginId: "",
                password: "",
            },
            signUpForm: {
                loginId: "",
                password: "",
                name: "",
                email: "",
            },
            findIdEmail : "",
            findPasswordForm: {
                loginId: "",
                email: "",
            }
        };
    },
    props: {
        isFixed: Boolean,
        menu: Object,
    },
    computed: {
        ...mapState("userStore", ["userInfo", "isLogin"]),
        rootPath: function () { 
            return process.env.VUE_APP_API;
        }
    },
    watch: {
        userInfo(newValue) { 
            this.avatarSrc = (newValue == null) ? "" : this.rootPath+`/image/`+newValue.profileImgPath;
        }
    },
    setup() { },
    created() { 
        if (this.userInfo && this.userInfo.profileImgPath) { 
            this.avatarSrc = this.rootPath+`/image/` + this.userInfo.profileImgPath;
        }
    },
    unmounted() { },
    methods: {
        ...mapActions("userStore", ["userConfirm"]),
        ...mapMutations("userStore", {'logout' : "LOGOUT"}),
        async confirm() {
            await this.userConfirm(this.signInForm);
            this.$router.go(0);
        },

        changeMenu(e) {
            const router = this.$router;
            this.$emit("menu-change", this.menus[e.target.value]);
            router.onReady(() => {
                router.push({ name: this.menus[e.target.value].routeName }).catch(() => { });
            });
        },
        changeMenuByAsset() { 
            this.$emit("menu-change", this.menus[0]);
            const router = this.$router;
            router.onReady(() => {
                router.push({ name: this.menus[0].routeName }).catch(() => { });
            });
        },
        toggleModal() {
            this.modalConfirmType ^= 1;
        },
        confirmModal() {
            if (this.tabIndex === 0) {
                this.confirm();
            } else {
                signUp(this.signUpForm, () => {
                    alert("회원가입에 성공했습니다.");
                });
            }
        },
        confirmAuthModal() {
             if (this.findAuthIndex === 0) {
                sendFindIdEmail({email: this.findIdEmail}, (response)=>{
                    alert(response.data);
                });
             } else {
                sendFindPassword(this.findPasswordForm, (response)=>{
                    alert(response.data);
                })
             }
        },
        userAction(idx) {
            if(idx == 0) {
                this.logout();
                alert("로그아웃 되었습니다.");
                this.$router.push({name: 'GalleryList'}).catch(() => { });
            } else {
                this.$emit("menu-change", {
                    value: "3",
                    key: "nokey",
                    text : "My Page",
                });
                this.$router.push({name:"MyPage"}).catch(()=>{});
            }
        },
        beforeLogin(event) {
            if(event.target.value > 1) {
                this.$bvModal.show('findIdModal');
            } else {
                this.$bvModal.show('userModal');
            }
        }
    }
}
</script>

<style scoped>
.navbar-static-height {
    background-color: white;
    height: 102px;
    padding: 0px 300px;
}
.simple-text-style {
    color: black;
    font-size: 1.5rem;
    background-color: white;
    font-weight: bolder;
}
.logo {
    width:auto; height: 80%;
}
</style>

<style>
.userModalBody {
    padding: 1.2rem 1rem 0rem 1rem;
}
.userModalFooter {
    padding: 0rem 1rem 1rem 0.7rem;
}
</style>