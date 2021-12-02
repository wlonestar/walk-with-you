import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 分页获取音乐
export function getMusicsByPage (page: number, size: number) : Promise<AxiosResponse> {
  return request({
    url: '/music/page',
    method: 'get',
    params: {
      page: page,
      size: size
    }
  })
}

// 获取所有音乐
export function getAllMusics () : Promise<AxiosResponse> {
  return request({
    url: '/music/all',
    method: 'get'
  })
}

// 根据 id 获取音乐
export function getMusicById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/music/',
    method: 'get',
    params: {
      id: id
    }
  })
}

// 添加音乐
export function addMusic (music: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/music/',
    method: 'post',
    data: music
  })
}

// 根据 id 更新音乐
export function updateMusic (id: number, music: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/music/',
    method: 'put',
    params: id,
    data: music
  })
}

// 删除音乐
export function deleteMusic (id: number) : Promise<AxiosResponse> {
  return request({
    url: `/music/${id}`,
    method: 'delete'
  })
}
