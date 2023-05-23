<template>
<div class="single-comment">
  <b-card no-body class="card mt-1">
    <div class="d-flex flex-column align-items-start justify-content-center mt-2 p-2">
      <div class="d-flex justify-content-between w-100 mt-1 px-3">
        <div class="d-flex"> 
          <p class="writer m-0" >작성자 :
            <span class="name font-weight-bold pt-1" >
              <!-- 수정 -->
              {{ comment.name }} 
            </span>
          </p>
          <p class="time my-0 ml-3 font-italic" >
            {{ comment.createdAt }}
          </p>
        </div>
        <div class="d-flex" v-if="userInfo && userInfo.userId == comment.userId">
          <b-button variant="none" @click="deleteComment">
            <b-icon class="mr-1 font-weight-bold font-red" icon="x-lg"></b-icon>
          </b-button>
        </div>
      </div>
      <div class="mt-2 px-3">
        <p class="text-left">
          {{ comment.content }}
        </p>
      </div>
    </div>
  </b-card>
</div>
</template>

<script>
import { mapState } from "vuex";

export default {
    name: 'TheComment',
    props: {
      comment: Object,
    },
    components: {},
    data() {
        return {
        };
    },
    computed: {
        ...mapState("userStore", ["userInfo"]),
    },
    methods: {
      deleteComment() {
          this.$emit('deleteComment', this.comment.commentId);
      },
    }
}
</script>

<style scoped>
.single-comment {
  margin-top: 10px;
}
.card {
  padding: 0px;
}
.writer {
  font-size: 1.2rem;
}
.name {
  text-decoration: underline;
}
.time {
  font-size: 0.8rem;
  padding-top: 0.3rem
}
</style>