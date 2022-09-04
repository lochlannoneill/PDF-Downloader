from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
import time
import os
import logging

LOG_FILENAME = 'exception.log'
logging.basicConfig(filename=LOG_FILENAME, level=logging.ERROR)

youtube = "https://www.youtube.com/results?search_query="
download = "https://ytmp3.cc/"


def initialize_parameters():
    with open('config.txt') as f:
        line = f.readline()
        return line


def wait_for_downloads(user):
    files = os.listdir("C:/Users/" + user + "/Desktop/mp3/")
    while True:
        if ".crdownload" in "".join(map(str, files)):
            files = os.listdir("C:/Users/" + user + "/Desktop/mp3/")
            pass
        else:
            return True


def rename_file(download_name, new_name, user):
    time.sleep(1)
    old_name = r"C:/Users/" + user + "/Desktop/mp3/" + download_name
    time.sleep(1)
    new_name = r"C:/Users/" + user + "/Desktop/mp3/" + new_name + ".mp3"
    os.rename(old_name, new_name)


def main():
    user = initialize_parameters()
    print("----- Welcome to Redux's Music Downloader -----")
    x = True
    while x:
        search_name = input("Enter song name: ")
        print("Your song is being downloaded...")

        s = Service('./chromedriver.exe')

        chrome_options = webdriver.ChromeOptions()
        download_location = {"profile.default_content_settings.popups": 0,
                             "download.default_directory":
                                 r"C:\Users\%s\Desktop\mp3\\" % user,  # IMPORTANT - ENDING SLASH V IMPORTANT
                             "directory_upgrade": True}
        chrome_options.add_experimental_option("prefs", download_location)
        chrome_options.add_argument("--headless")
        chrome_options.add_argument("--mute-audio")

        driver = webdriver.Chrome(service=s, options=chrome_options)

        driver.get(youtube + search_name)

        time.sleep(2)
        btn = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.XPATH, '//*[@id="content"]/div[2]/div[6]/div[1]/ytd-button-renderer[1]'))
        btn.click()

        song_name = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.XPATH, '//*[@id="video-title"]/yt-formatted-string').text)

        invalid = '?*":<>\|/'
        for char in invalid:
            song_name = song_name.replace(char, "")

        btn2 = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.XPATH, '//*[@id="dismissible"]/ytd-thumbnail'))
        btn2.click()

        time.sleep(1)
        song_url = driver.current_url

        driver.get(download)

        #Go to Download
        url_input = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.XPATH, '//*[@id="input"]'))
        url_input.send_keys(song_url)

        time.sleep(1)

        convert_btn = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.XPATH, '//*[@id="submit"]'))
        time.sleep(1)
        convert_btn.click()

        download_btn = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.CSS_SELECTOR, '#download'))
        time.sleep(2)
        download_btn = WebDriverWait(driver, 10).until(
            lambda x: x.find_element(By.CSS_SELECTOR, '#download'))
        download_btn.click()

        path = '/Users/%s/Desktop/mp3' % user
        is_exist = os.path.exists(path)

        if not is_exist:
            os.makedirs(path)

        time.sleep(3)

        while True:
            has_downloaded = wait_for_downloads(user)
            if has_downloaded:
                break

        print("Downloaded successfully as : " + song_name)

        keep_downloading = input("\nDownload more y/n ?: ")
        if keep_downloading == "y":
            continue
        else:
            print("\nAll songs downloaded to Desktop/mp3 folder")
            print("Thank you for using my app :)")
            break


try:
    main()
except Exception as e:
    logging.exception("Crashed error: %s", e)
