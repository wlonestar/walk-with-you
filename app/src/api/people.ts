import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 分页获取音乐
export function getPeoplesByPage (page: number, size: number) : Promise<AxiosResponse> {
  return request({
    url: '/people/page',
    method: 'get',
    params: {
      page: page,
      size: size
    }
  })
}

// 获取所有音乐
export function getAllPeoples () : Promise<AxiosResponse> {
  return request({
    url: '/people/all',
    method: 'get'
  })
}

// 根据 id 获取音乐
export function getPeopleById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/people/',
    method: 'get',
    params: id
  })
}

// 添加音乐
export function addPeople (people: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/people/',
    method: 'post',
    data: people
  })
}

// 根据 id 更新音乐
export function updatePeople (id: number, people: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/people/',
    method: 'put',
    params: id,
    data: people
  })
}

// 删除音乐
export function deletePeople (id: number) : Promise<AxiosResponse> {
  return request({
    url: `/people/${id}`,
    method: 'delete'
  })
}
