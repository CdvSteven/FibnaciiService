from behave import *
import urllib.request, http.cookiejar

# define a http url request tool
class httpurl:
	def __init__(self, ua='Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.101 Safari/537.36'):
		ck=http.cookiejar.CookieJar()
		self.opener=urllib.request.build_opener(urllib.request.HTTPCookieProcessor(ck))
		self.opener.addheaders=[('User-Agent',ua)]

	def get(self, url):
		bytes=self.opener.open(url).read()
		return bytes.decode('utf-8').strip()

def fibs(n, ip="localhost", port=8080):
    return httpurl().get("http://%s:%s/FibonacciNumber/Fibonacci.do?number=%d&Submit=submit" %(ip, port, n))

@given('we have the number {input}')
def have_number(context,input):
    context.input = int(input)

@when('we call the web service')
def call_web_service(context):
    context.result=fibs(context.input)

@then('we check the result {output}')
def check_result(context,output):
    assert context.result == output, "fibonacci input: %d, expected: %s, while we get %s" %(context.input, output, context.result)

