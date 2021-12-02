<template>
  <div>
    <div style="padding: 50px 50px 50px 50px;">
      <n-row gutter="20">
        <n-col :span="14">
          <el-carousel :interval="5000" arrow="always">
            <el-carousel-item v-for="newz in news" :key="newz.id">
              <a :href='newz.link' class="pic_item">
                <img class="carousel-img" alt="" :src='newz.photo'>
                <h3>{{ newz.title }}</h3>
              </a>
            </el-carousel-item>
          </el-carousel>
        </n-col>
        <n-col :span="10">
          <div>
            <div class="news" v-for="newsss in newss" :key="newsss.id">
              <a :href='newsss.link'> {{ newsss.title }} </a>
            </div>
          </div>
        </n-col>
      </n-row>
    </div>
  </div>
</template>

<script>
import { defineComponent } from 'vue'
import { NRow, NCol } from 'naive-ui'

export default defineComponent({
  name: 'News',
  components: {
    NRow,
    NCol
  },
  setup () {
    const news = [
      {
        id: 0,
        title: '党史学习教育中央宣讲团首场报告会',
        photo: 'https://boot-img.xuexi.cn/lego/image/3591_ZFAvy5fRO/e0ee041e21bb49709110be2c9a5b060d.jpg',
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=2111671503191297319&item_id=2111671503191297319'
      },
      {
        id: 1,
        title: '“中国共产党历史学习在线答问”系列访谈直播总阅读量达1.7亿人次',
        photo: 'https://boot-img.xuexi.cn/image/1005/process/2869596c25224617b1b202213ffbbdd9.jpg',
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=8070814162660199547&item_id=8070814162660199547'
      },
      {
        id: 2,
        title: '中国共产党历史学习在线答问（第八场）',
        photo: 'https://boot-img.xuexi.cn/lego/image/3591_ZFAvy5fRO/ed81836cee8742f18925c9b0cad300dc.jpg',
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=12469846631016835546&item_id=12469846631016835546'
      },
      {
        id: 3,
        title: '中共中央宣传部发布中国共产党成立100周年庆祝活动标识',
        photo: 'https://boot-img.xuexi.cn/lego/image/3591_ZFAvy5fRO/ec76a15bc73d4832a7a96924840efb12.jpg',
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=990851744743133367&item_id=990851744743133367'
      }
    ]
    const newss = [
      {
        id: 0,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=18105518996199773410&item_id=18105518996199773410',
        title: '习近平：坚定理想信念 补足精神之钙'
      },
      {
        id: 1,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=237613134931265410&item_id=237613134931265410',
        title: '习近平：用好红色资源 赓续红色血脉 努力创造无愧于历史和人民的新业绩'
      },
      {
        id: 2,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=4777457587162082115&item_id=4777457587162082115',
        title: '湖北：走深走实学党史 用心用情办实事'
      },
      {
        id: 3,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=3532302623218363236&item_id=3532302623218363236',
        title: '新疆兵团：完成“我为群众办实事”项目18925个'
      },
      {
        id: 4,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=6533495329297046393&item_id=6533495329297046393',
        title: '山西大同平城区：家门口有了小公园 居民收获“小欢喜”'
      },
      {
        id: 5,
        link: 'https://www.xuexi.cn/lgpage/detail/index.html?id=15188945095247228638&item_id=15188945095247228638',
        title: '浙江海宁：缓解停车难  市区新增停车位1000多个'
      }
    ]
    return {
      news,
      newss
    }
  }
})
</script>

<style scoped>
.carousel-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.news {
  margin-bottom: 20px;
  font-size: 18px;
}
.el-carousel__item h3 {
  color: #e2e2e2;
  font-size: 1rem;
  opacity: 0.75;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}
.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.block {
  width: 100%;
}
.small {
  width: 100%;
  height: 100%;
  position: relative;
}
.pic_item {
  position: relative;
  height: 100%;
}
.pic_item:hover{
  cursor: pointer;
}
.pic_item img {
  width: 100%;
  height: 100%;
}
.pic_item h3 {
  position: absolute;
  left: 1rem;
  bottom: 2rem;
}
</style>
