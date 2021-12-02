<template>
  <div>
    <Nav></Nav>
    <div>
      <div class="video">
        <div>
          <img src="../assets/img/标题四.png" class="subtitle3" alt="">
        </div>
        <card>
          <div v-for="video in videos" :key="video.id" class="hero-card">
            <el-row>
              <el-col :span="5">
                <div>
                  <a @click="this.$router.push(`/video/${video.id}`)">
                    <img :src='video.poster' style="height: 250px;" alt="">
                  </a>
                </div>
              </el-col>
              <el-col :span="19">
                <div>
                  <div class="videoname">
                    <a @click="this.$router.push(`/video/${video.id}`)">{{ video.name }}</a>
                  </div>
                  <div class="videointro">{{ video.introduction }}</div>
                </div>
              </el-col>
            </el-row>
          </div>
        </card>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getAllVideos } from '../api/video.ts'
import Nav from '../components/Nav'
import Footer from '../components/Footer'

export default defineComponent({
  name: 'Video',
  components: { Footer, Nav },
  data () {
    return {
      videos: [
        {
          id: 0,
          name: '',
          poster: '',
          link: '',
          introduction: ''
        }
      ]
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getAllVideos().then(response => {
        this.videos = response.data
      })
    }
  }
})
</script>

<style scoped>
a {
  cursor: pointer;
  color: #eaeaea;
}
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
</style>
