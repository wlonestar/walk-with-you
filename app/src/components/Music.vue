<template>
  <div>
    <Nav></nav>
    <div>
      <div class="music">
        <div>
          <img src="../assets/img/标题三.png" class="subtitle4" alt="">
        </div>
        <div class="mycard">
          <div class="name" style="color: #e2e2e2"><h2>{{ music.name }}</h2></div>
          <div style="margin-bottom: 20px; margin-top: 20px;">
            <iframe :src='link' frameborder="no" border="0"
              marginwidth="0" marginheight="0" width=330 height=86>
            </iframe>
          </div>
          <div class="musicname">{{ music.background }}</div>
          <br />
          <div class="intro">{{ music.appreciation }}</div>
        </div>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getMusicById } from '../api/music.ts'
import { neteasePrefix, neteaseSuffix } from '../util/constants.ts'
import Nav from './Nav'
import Footer from './Footer'

export default defineComponent({
  name: 'Music',
  components: { Footer, Nav },
  data () {
    return {
      link: '',
      music: {
        id: 0,
        name: '',
        link: '',
        background: '',
        appreciation: ''
      }
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getMusicById(this.$route.params.id).then(response => {
        console.log(response.data)
        this.music = response.data
        this.link = neteasePrefix + this.music.link + neteaseSuffix
      })
    }
  }
})
</script>

<style scoped>
.mycard {
  margin: 30px 5% 30px 5%;
}
.musicname {
  font-size: 16px;
  margin-bottom: 15px;
  color: #eaeaea;
}
.intro {
  font-size: 16px;
  color: #e2e2e2;
}
</style>
