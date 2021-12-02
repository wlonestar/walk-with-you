import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 获取所有视频
export function getAllVideos () : Promise<AxiosResponse> {
  return request({
    url: '/video/all',
    method: 'get'
  })
}

// 根据 id 获取视频
export function getVideoById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/video/',
    method: 'get',
    params: {
      id: id
    }
  })
}
