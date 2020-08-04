import requests
from bs4 import BeautifulSoup
import pandas as pd
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
from apscheduler.schedulers.blocking import BlockingScheduler
def exec_interval():
    driver = webdriver.Chrome("./chromedriver.exe")
    url = "https://www.campuspick.com/contest?category=108"
    driver.get(url)
    project_list = []
    project_detail = driver.find_elements_by_css_selector('#container .list .item a')
    # print(project_detail)
    for detail in project_detail:
        a = detail.get_attribute("href")
        if (a in project_list) or ('menu' in a):
            continue
        else:
            project_list.append(a)
    for url in project_list:
        driver.get(url)
        project_name = driver.find_elements_by_css_selector("#container .section .dday")[0].text
        if project_name == "마감":
            continue
        else:
            title = driver.find_elements_by_css_selector('body > section.title > div > div > h1')[0].text
            host = driver.find_elements_by_css_selector('#container > div:nth-child(2) > p:nth-child(3)')[0].text
            price = driver.find_elements_by_css_selector('#container > div:nth-child(3) > p:nth-child(3)')[0].text
            if driver.find_elements_by_css_selector('#container > div:nth-child(3) > p:nth-child(5)'):
                total_price = driver.find_elements_by_css_selector('#container > div:nth-child(3) > p:nth-child(5)')[0].text
            else:
                total_price = '정보없음'
            summary = driver.find_elements_by_css_selector('#container > div:nth-child(5) > article')[0].text
            print('공모전명: ', title)
            print('주최: ', host)
            print('1등 상금: ' ,price)
            print('총 시상금: ', total_price)
            print('내용: ')
            print(summary)
            print('ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ')
sched = BlockingScheduler()
# 60초마다 실행하라. 초단위로 설정할 수 있다.
sched.add_job(exec_interval, 'interval', seconds=60)
sched.start()