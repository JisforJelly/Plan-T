<template>
    <div class="container-fluid p-0">
        <!-- Header -->
        <div class="header d-flex flex-column h-100">
            <div>
                <h2 class="font-weight-bold mb-0 w-auto"> 게시글 상세 조회 </h2>
            </div>
            <div class="board-content d-flex align-items-center justify-content-between">
                <div class="d-flex flex-grow-2 w-auto">
                    <b-button class="button mr-2" @click="movePostList">목록</b-button>
                    <b-button class="button mr-2" @click="modifyPost" v-if="userInfo && userInfo.userId == post.userId">게시글 수정</b-button>
                    <b-button class="button btn-danger" @click="deletePost" v-if="userInfo && userInfo.userId == post.userId">게시글 삭제</b-button>
                </div>
            </div>
        </div>

        <!-- Board Section -->
        <div class="board-section">
            <!-- 게시글 표시 영역 -->
            <b-card bg-variant="light" no-body class="post-area" >
                <b-card class="content">
                    <p v-html="post.title" align="left" class="post-title" ></p>
                    
                    <div class="information d-flex flex-row-reverse align-items-center mt-1">                
                        <p class="time my-0 ml-3 font-italic" >
                            {{ post.createdAt }}
                        </p>
                        <p class="writer m-0" >작성자 :
                            <span class="name font-weight-bold pt-1">
                                    {{ post.name }}
                            </span>
                        </p>
                    </div>
                    <p v-html="post.content" align="left" class="post-content"></p>
                </b-card>
            </b-card>

            <!-- 댓글 등록 form-->
            <b-card bg-variant="light" class="comment">
                <div class="mt-1 d-flex flex-column">
                    <b-form-textarea v-model="commentInsertForm" placeholder="댓글을 입력하세요"></b-form-textarea>
                    <div class="d-flex mt-1  flex-row-reverse">
                        <b-button class="button" @click="pushComment">댓글 등록</b-button>
                    </div>
                </div>
            </b-card>

            <!-- 댓글 표시 영역 -->
            <TheComment :comment="comment" v-for="comment in comments" :key="comment.commentId" v-on:deleteComment="deleteComment"/>
            
        </div>
    </div>

    
</template>
<script>
import { getPostByPostId, deletePost } from "@/api/post"
import { getComments, insertComment, deleteComment } from "@/api/comment"
import TheComment from "@/components/comment/TheComment.vue"
import { mapState } from "vuex";

export default {
    name:'FreeBoardDetail',
    components: {
        TheComment,
    },
    data() {
        return {
            postId : -1,
            readOnly: true,
            post: {},
            comments: [],
            customToolbar: [
                ["bold", "italic", "underline"],
                [{ list: "ordered" }, { list: "bullet" }],
                ["code-block"]
            ],
            commentInsertForm : "",
        };
    },
    setup() {},
    created() {
        this.postId = this.$route.params.no;
        this.renderPost();
    },
    computed: {
        ...mapState("userStore", ["userInfo"]),
    },
    mounted() {},
    unmounted() {},
    methods: {
        async renderPost() {
            await getPostByPostId(this.postId, (response) => {
                this.post = response.data;
            });

            await getComments(this.postId, (response)=>{
                console.log(response.data.comments)
                this.comments = response.data.comments; // 수정
            });
        },
        movePostList() { 
            this.$router.push({ name: "FreeBoard" }).catch(() => { });
        },
        modifyPost() {
            this.$router.push({ name: "FreeBoardModify", params: { 'no': this.postId } }).catch(() => { });
        },
        deletePost() { 
            if(confirm("정말 삭제하시겠습니까?")) {
                deletePost(this.postId, () => {
                    this.$router.push({ name: "FreeBoard" }).catch(() => { });
                });
            }
        },
        pushComment() {
            if(!this.commentInsertForm) {
                alert("입력된 값이 없습니다.")
                return;
            }
            insertComment({
                postId: this.postId,
                content: this.commentInsertForm,
            }, ()=>{
                this.renderPost();
                this.commentInsertForm = "";
            });
        },
        deleteComment(commentId) {
            if(confirm("정말 삭제하시겠습니까?")) {
                deleteComment(commentId, ()=>{
                    this.renderPost();
                })
            }
        }
    }
}
</script>

<style scoped>
.header {
    padding: 20px 300px;
    margin-top: 60px;
    min-width: 1100px !important;
}
.board-section {
    padding: 20px 300px;
    height: 2000px;
    min-width: 1100px !important;
}
.board-content {
    margin-top: 60px;
}
.content {
    border:none;
}
.information {
     border-bottom: 1px solid #e3e7eb;
     padding-bottom: 10px;
}
.post-content {
    padding: 20px 20px;
}
.post-title {
    padding: 20px 20px;
    background-color:white;
    font-size: 20px;
    border-bottom: 1px solid #e3e7eb;
}
.post-area {
    padding: 20px;
}
.button {
    white-space: nowrap;
}
.writer {
    font-size: 0.8rem;
}
.name {
    text-decoration: underline;
}
.time {
    font-size: 0.8rem;
}
.comment {
    margin-top: 10px;
}
</style>