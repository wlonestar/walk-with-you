import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 分页获取事件
export function getAllEventByPage (page: number, size: number) : Promise<AxiosResponse> {
  return request({
    url: '/event/page',
    method: 'get',
    params: {
      page: page,
      szie: size
    }
  })
}

// 获取所有事件
export function getAllEvents () : Promise<AxiosResponse> {
  return request({
    url: '/event/all',
    method: 'get'
  })
}

// 根据 id 获取事件
export function getEventById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/event/',
    method: 'get',
    params: {
      id: id
    }
  })
}

// 添加事件
export function addEvent (event: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/event/',
    method: 'post',
    data: event
  })
}

// 根据 id 更新事件
export function updateEvent (id: number, event: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/event/',
    method: 'put',
    params: id,
    data: event
  })
}

// 删除事件
export function deleteEvent (id: number) : Promise<AxiosResponse> {
  return request({
    url: `/event/${id}`,
    method: 'delete'
  })
}
