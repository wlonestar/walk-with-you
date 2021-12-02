<template>
  <div>
    <Nav></Nav>
    <div>
      <div class="video">
        <div>
          <img src="../assets/img/标题四.png" class="subtitle3" alt="">
        </div>
        <div class="hero-card">
          <div class="iframes">
            <iframe :src='link' allowfullscreen="allowfullscreen"
              width="100%" height="100%" scrolling="no" frameborder="0"
              sandbox="allow-top-navigation allow-same-origin allow-forms allow-scripts">
            </iframe>
          </div>
          <div style="margin-top: 20px;">
            <div class="videoname">{{ video.name }}</div>
            <div class="videointro">{{ video.introduction }}</div>
          </div>
        </div>
        <div class="videorow">
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getVideoById } from '../api/video.ts'
import { bilibiliPrefix, bilibiliSuffix } from '../util/constants'
import Nav from './Nav'
import Footer from './Footer'

export default defineComponent({
  name: 'Video',
  components: { Footer, Nav },
  data () {
    return {
      link: '',
      video: {
        id: 0,
        name: '',
        poster: '',
        link: '',
        introduction: ''
      }
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getVideoById(this.$route.params.id).then(response => {
        console.log(response.data)
        this.video = response.data
        this.link = bilibiliPrefix + this.video.link + bilibiliSuffix
      })
    }
  }
})
</script>

<style scoped>
.hero-card {
  margin-left: 50px;
  margin-right: 50px;
  margin-bottom: 20px;
}
.videoname {
  font-size: 20px;
  margin-bottom: 15px;
  color: #eaeaea;
}
.videointro {
  font-size: 15px;
  color: #e2e2e2;
}
.image {
  width: 100%;
  display: inline;
}
body {
  margin: 0;
}
.iframes {
  width: 100%;
  height: 600px;
}
.link-top {
  width: 100%;
  height: 1px;
  border-top: solid red 2px;
}
</style>
