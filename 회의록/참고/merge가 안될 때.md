![image-20200728170242002](C:\Users\multicampus\AppData\Roaming\Typora\typora-user-images\image-20200728170242002.png)



git branch -a

local과 remote에 있는 모든 브랜치가 나온다

---

step 1. git checkout -b feature/fe/login 은 원격에 있는 브랜치랑 같은 로컬 브랜치를 만들어서 수정하고 pull하려고 만드는거야

---



#### 방법

1. git branch -a
2. 원하는 브랜치에서
3. git pull origin develop
4. git merge --no-ff remotes/origin/reature/fe/login
5. mergin 충돌 해결하고
6. git push origin develop
7. git add .
8. git commit -m ""
9. git push origin develop
10. 충돌난 부분 해결하고 머지





충돌해결하고 싶은 브랜치에서 git pull origin develop

merging -> 충돌해결하고

git add .

git commit

S03P12B306-140