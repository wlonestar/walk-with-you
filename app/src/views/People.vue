<template>
  <div>
    <Nav></Nav>
    <div>
      <div class="character">
        <div>
          <img src="../assets/img/标题一.png" class="subtitle2" alt="">
        </div>
        <card>
          <div v-for="people in peoples" :key="people.id" class="hero-card">
            <el-row>
              <el-col :span="12">
                <div>
                  <div class="namerow">{{ people.name }}</div>
                  <img :src="people.picture" style="width: 500px;" alt=""/>
                </div>
              </el-col>
              <el-col :span="12">
                <div>
                  <div class="detail"><h3>事迹介绍：</h3>{{ people.introduction }}</div>
                  <div v-if="people.mention.length!==0" class="detail"><h3>习近平提到：</h3>{{ people.mention }}</div>
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
import { getAllPeoples } from '@/api/people.ts'
import Nav from '@/components/Nav.vue'
import Footer from '@/components/Footer.vue'

export default defineComponent({
  name: 'People',
  components: {
    Footer,
    Nav
  },
  data () {
    return {
      peoples: [
        {
          id: 0,
          name: '',
          picture: '',
          introduction: '',
          mention: ''
        }
      ]
    }
  },
  created () {
    this.load()
  },
  methods: {
    load () {
      getAllPeoples().then(response => {
        console.log(response.data)
        this.peoples = response.data
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

.namerow {
  color: black;
  font-size: 28px;
  font-weight: 200;
  font-family: "thumb", serif;
  margin-bottom: 10px;
}

.detail {
  font-size: 18px;
  color: #eaeaea;
  font-weight: 200;
  font-family: monospace;
  margin-bottom: 20px;
}
</style>
