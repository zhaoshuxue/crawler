# -*- coding: utf-8 -*-

import urllib.request

url = "https://weibo.com/like/outbox?leftnav=1"
req = urllib.request.Request(url, headers={
    'Accept': 'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
    # 'Accept-Encoding': 'gzip, deflate, sdch, br',
    'Accept-Language': 'zh-CN,zh;q=0.8',
    'Cache-Control': 'max-age=0',
    'Connection': 'keep-alive',
    'Host': 'weibo.com',
    'Upgrade-Insecure-Requests': '1',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36',

    'Cookie': 'SINAGLOBAL=3781604249112.9863.1507555359424; wvr=6; SSOLoginState=1510586427; SCF=Aqiw7wKJ-fcPjSBZyLMQ8ChPVvQSEzfavrRW4CeP_Xze6X98CGt7nFd7zVfklgmAD_qo5J2aD-HEXCd5hcOP8-U.; SUB=_2A253DchrDeThGeRH7FMU8ynFyj-IHXVUer6jrDV8PUNbmtANLXLGkW8AzNz6RrhJa7X4l5LqpOsdu0Po5w..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFDzNefysM2fSMz-Fqxy97r5JpX5KMhUgL.Foz4S02fe0M4eKe2dJLoIpDzIgUQdJH0MEH8SbHWeF-4eCH8SCHWxFHWSntt; SUHB=0A0Z0jWXR5kwj5; ALF=1542122426; _s_tentry=login.sina.com.cn; Apache=2573892439300.343.1510586435773; ULV=1510586435884:26:8:1:2573892439300.343.1510586435773:1510315547864; YF-Page-G0=734c07cbfd1a4edf254d8b9173a162eb; UOR=,,login.sina.com.cn; wb_cusLike_2971537913=N; YF-V5-G0=020421dd535a1c903e89d913fb8a2988; WBStorage=82ca67f06fa80da0|undefined'
})

oper = urllib.request.urlopen(req)
data = oper.read()
# data = urllib.request.urlopen(url).read()
data = data.decode('UTF-8')
# data = data.decode('GBK')
print(data)
