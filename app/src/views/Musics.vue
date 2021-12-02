<template>
  <div>
    <Nav></nav>
    <div>
      <div class="music">
        <div>
          <img src="../assets/img/标题三.png" class="subtitle4" alt="">
        </div>
        <card>
          <el-row>
            <el-col :span="9">
              <div style="margin-left: -40px; margin-top: -80px;">
                <img src="../assets/img/record.png" height="500" alt="">
              </div>
            </el-col>
            <el-col :span="15">
              <card style="margin-right: 100px;">
                <div v-for="music in musics" :key="music.id" class="name">
                  <a @click="this.$router.push(`/music/${music.id}`)">{{ music.name }}</a>
                </div>
              </card>
            </el-col>
          </el-row>
        </card>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { getAllMusics } from '@/api/music.ts'
import Nav from '@/components/Nav.vue'
import Footer from '@/components/Footer.vue'

export default defineComponent({
  name: 'Music',
  components: { Footer, Nav },
  data () {
    return {
      musics: [
        {
          id: 0,
          name: '',
          author: '',
          link: '',
          background: '',
          appreciation: ''
        }
      ]
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getAllMusics().then(response => {
        this.musics = response.data
      })
    }
  }
})
</script>

<style scoped>
a {
  color: #eaeaea;
  cursor: pointer;
}

.name {
  margin-bottom: 20px;
  margin-top: 20px;
  font-size: 20px;
}
</style>
