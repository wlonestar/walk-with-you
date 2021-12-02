<template>
  <div>
    <Nav></nav>
    <div>
      <div class="main">
        <News></News>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue'
import Nav from '@/components/Nav.vue'
import Footer from '@/components/Footer.vue'
import News from '@/components/News.vue'

export default defineComponent({
  name: 'Home',
  components: {
    Footer,
    Nav,
    News
  }
})
</script>

<style scoped>
.main{
  position: relative;
  width: 1200px;
  height: 150%;
  margin: 15px auto;
  background-repeat: no-repeat;
  background-size: cover;
  border-radius: 10px;
  box-shadow: 5px 5px 9px 3px #bfbfbf;
}
</style>
