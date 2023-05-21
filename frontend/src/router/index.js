import Vue from "vue";
import VueRouter from "vue-router";

import GalleryPage from "@/views/GalleryPage";
import GalleryList from "@/components/gallery/GalleryList";
import GalleryRegist from "@/components/gallery/GalleryRegist";
import GalleryDetail from "@/components/gallery/GalleryDetail";

import TripPlan from "@/views/TripPlan";

import BoardPage from "@/views/BoardPage";
import FreeBoard from "@/components/board/FreeBoard";
import FreeBoardRegist from "@/components/board/FreeBoardRegist";
import FreeBoardDetail from "@/components/board/FreeBoardDetail";
import FreeBoardModify from "@/components/board/FreeBoardModify";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/hotplace",
  },
  {
    path: "/hotplace",
    name: "HotPlace",
    component: GalleryPage,
    children: [
      {
        path: "",
        name: "GalleryList",
        component: GalleryList,
      },
      {
        path: "regist",
        name: "GalleryRegist",
        component: GalleryRegist,
      },
      {
        path: "detail/:no",
        name: "GalleryDetail",
        component: GalleryDetail,
      },
    ],
  },
  {
    path: "/board",
    name: "BoardPage",
    component: BoardPage,
    children: [
      {
        path: "",
        name: "FreeBoard",
        component: FreeBoard,
      },
      {
        path: "regist",
        name: "FreeBoardRegist",
        component: FreeBoardRegist,
      },
      {
        path: "post/:no",
        name: "FreeBoardDetail",
        component: FreeBoardDetail,
      },
      {
        path: "modify/post/:no",
        name: "FreeBoardModify",
        component: FreeBoardModify,
      },
    ],
  },
  {
    path: "/trip",
    name: "TripPlan",
    component: TripPlan,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
