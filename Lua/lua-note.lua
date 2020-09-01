#!/usr/local/bin/lua

print("Hello World!")

print("Lua 语句无需加分号结尾、流程控制语句无需加大括号")

-- comment
print("单行注释 --")

--[[
多行
注释
--]]
print("多行注释 --[[ --]]")

print("最好不要使用下划线加大写字母的标示符，因为Lua的保留字也是这样的")

print("Lua 是动态类型语言")

print("Lua 中有 8 个基本类型分别为：nil、boolean、number、string、userdata、function、thread 和 table")
print(type("hello"))
print(type(10.4*3))
print(type(print))
print(type(type))
print(type(true))
print(type(nil))
print(type(type(X)))

print("Lua 把 false 和 nil 看作是 false，其他的都为 true，数字 0 也是 true")

-- 创建一个空的 table
local tbl1 = {}

-- 直接初始表
local tbl2 = {"apple", "pear", "orange", "grape"}

a = {}
a["key"] = "value"
key = 10
a[key] = 22
a[key] = a[key] + 11
for k, v in pairs(a) do
    print(k .. " : " .. v)
end

local tbl = {"apple", "pear", "orange", "grape"}
for key, val in pairs(tbl) do
    print("Key", key)
end

a3 = {}
for i = 1, 10 do
    a3[i] = i
end
a3["key"] = "val"
print(a3["key"])
print(a3["none"])

function factorial1(n)
    if n == 0 then
        return 1
    else
        return n * factorial1(n - 1)
    end
end
print(factorial1(5))
factorial2 = factorial1
print(factorial2(5))

function testFun(tab,fun)
        for k ,v in pairs(tab) do
                print(fun(k,v));
        end
end

tab={key1="val1",key2="val2"};
testFun(tab,
function(key,val)--匿名函数
        return key.."="..val;
end
);

print("Lua 中的变量全是全局变量，那怕是语句块或是函数里，除非用 local 显式声明为局部变量")

i = 1;
while(i < 3)
do 
    print("while loop")
    i = i+1
end

for var=1,3 do
    print("for loop " .. var)
end

--[ 变量定义 --]
a = 10
----[ 执行循环 --]
repeat
   print("repeat a的值为:", a)
   a = a + 1
until( a > 15 )

s, e = string.find("www.runoob.com", "runoob")
print("函数可以返回多个结果值，比如string.find，其返回匹配串\"开始和结束的下标\"(如果不存在匹配串返回nil)")
print(s .. " " .. e)

function maximum (a)
    local mi = 1             -- 最大值索引
    local m = a[mi]          -- 最大值
    for i,val in ipairs(a) do
       if val > m then
           mi = i
           m = val
       end
    end
    return m, mi
end

print("在return后列出要返回的值的列表即可返回多值")
print(maximum({8,10,23,12,5}))

function add(...)  
local s = 0  
  for i, v in ipairs{...} do   
    s = s + v  
  end  
  return s  
end  
print(add(3,4,5,6,7))  --->25

function average(...)
   result = 0
   local arg={...}    --> arg 为一个表，局部变量
   for i,v in ipairs(arg) do
      result = result + v
   end
   print("总共传入 " .. #arg .. " 个数")
   return result/#arg
end
print("平均值为",average(10,5,3,4,5,6))

do  
    function foo(...)  
        for i = 1, select('#', ...) do  -->获取参数总数
            local arg = select(i, ...) -->读取参数
            print("arg " .. arg)  
        end  
    end  
 
    foo(1, 2, 3, 4)  
end

print([==[
^
not    - (unary)
*      /
+      -
..
<      >      <=     >=     ~=     ==
and
or
]==])
print("除了 ^ 和 .. 外所有的二元运算符都是左连接的")

string1 = "Lua"
print("\"字符串 1 是\"",string1)
string2 = 'runoob.com'
print("字符串 2 是",string2)

string3 = [["Lua 教程"]]
print("字符串 3 是",string3)

function square(iteratorMaxCount,currentNumber)
   if currentNumber<iteratorMaxCount
   then
      currentNumber = currentNumber+1
   return currentNumber, currentNumber*currentNumber
   end
end

for i,n in square,3,0
do
   print(i,n)
end

fruits = {"banana","orange","apple","grapes"}
print("排序前")
for k,v in ipairs(fruits) do
        print(k,v)
end

table.sort(fruits)
print("排序后")
for k,v in ipairs(fruits) do
        print(k,v)
end

require("module")
print(module.constant)
module.func3()

local m = require("module")
print(m.constant)
m.func3()

mytable = {}                          -- 普通表
mymetatable = {}                      -- 元表
print(setmetatable(mytable,mymetatable))     -- 把 mymetatable 设为 mytable 的元表
print(getmetatable(mytable))

mytable = setmetatable({key1 = "value1"}, {
  __index = function(mytable, key)
    if key == "key2" then
      return "metatablevalue"
    else
      return nil
    end
  end
})
print(mytable.key1,mytable.key2)

mytable = setmetatable({key1 = "value1"}, { __index = { key2 = "metatablevalue" } })
print(mytable.key1,mytable.key2)

function table_maxn(t)
    local mn = 0
    for k, v in pairs(t) do
        if mn < k then
            mn = k
        end
    end
    return mn
end
-- 两表相加操作
mytable = setmetatable({ 1, 2, 3 }, {
  __add = function(mytable, newtable)
    for i = 1, table_maxn(newtable) do
      table.insert(mytable, table_maxn(mytable)+1,newtable[i])
    end
    return mytable
  end
})

secondtable = {4,5,6}

mytable = mytable + secondtable
for k,v in ipairs(mytable) do
    print(k,v)
end

mytable = setmetatable({ 10, 20, 30 }, {
  __tostring = function(mytable)
    sum = 0
    for k, v in pairs(mytable) do
                sum = sum + v
    end
    return "表所有元素的和为 " .. sum
  end
})
print(mytable)

print("coroutine")
co = coroutine.create(
    function(i)
        print(i);
    end
) 
coroutine.resume(co, 1)
print(coroutine.status(co))

co = coroutine.wrap(
    function(i)
        print(i);
    end
)
co(1)

co2 = coroutine.create(
    function()
        for i=1,10 do
            print(i)
            if i == 3 then
                print(coroutine.status(co2))  --running
                print(coroutine.running()) --thread:XXXXXX
            end
            coroutine.yield()
        end
    end
)
coroutine.resume(co2) --1
coroutine.resume(co2) --2
coroutine.resume(co2) --3
print(coroutine.status(co2))   -- suspended
print(coroutine.running())

print("resume 处于主程中，它将外部状态(数据)传入到协同程序内部; 而 yield 则将内部的状态(数据)返回到主程中")
function foo (a)
    print("foo 函数输出", a)
    return coroutine.yield(2 * a) -- 返回  2*a 的值
end
co = coroutine.create(function (a , b)
    print("第一次协同程序执行输出", a, b) -- co-body 1 10
    local r = foo(a + 1)
     
    print("第二次协同程序执行输出", r)
    local r, s = coroutine.yield(a + b, a - b)  
     
    print("第三次协同程序执行输出", r, s)
    return b, "结束协同程序"                   
end)       
print("main", coroutine.resume(co, 1, 10)) -- true, 4
print("--分割线----")
print("main", coroutine.resume(co, "r")) -- true 11 -9
print("---分割线---")
print("main", coroutine.resume(co, "x", "y")) -- true 10 end
print("---分割线---")
print("main", coroutine.resume(co, "x", "y")) -- cannot resume dead coroutine
print("---分割线---")

print("生产者-消费者")
local newProductor
function productor()
     local i = 0
     while true do
          i = i + 1
          send(i)     -- 将生产的物品发送给消费者
     end
end
function consumer()
     while true do
          local i = receive()     -- 从生产者那里得到物品
          print(i)
          if i >= 6 then
              break
	  end
     end
end
function receive()
     local status, value = coroutine.resume(newProductor)
     return value
end
function send(x)
     coroutine.yield(x)     -- x表示需要发送的值，值返回以后，就挂起该协同程序
end
-- 启动程序
newProductor = coroutine.create(productor)
consumer()

-- 以只读方式打开文件
file = io.open("lua-note.lua", "r")
-- 设置默认输入文件
io.input(file)
-- 输出文件第一行
print(io.read())
-- 关闭打开的文件
io.close(file)
file = io.open("read.txt", "r")
io.input(file)
-- 读取一个数字并返回它
print(io.read("*n"))
print(io.read("*n"))
-- 返回一个指定字符个数的字符串
print(io.read(5))

-- 以附加的方式打开只写文件
file = io.open("test.txt", "a")
-- 设置默认输出文件
io.output(file)
-- 在文件最后一行添加 Lua 注释
-- io.write("-- test.txt 文件末尾注释\n")
print("向文件写入缓冲中的所有数据")
io.flush()
io.close(file)
-- 返回一个临时文件句柄，程序结束时自动删除
file = io.tmpfile()
print("检测obj是否一个可用的文件句柄")
print(io.type(file))

print("返回一个迭代函数,每次调用将获得文件中的一行内容,当到文件尾时，将返回nil")
f = io.lines("read.txt")
print(type(f))
print(f())
print(f())

print("以一种面对对象的形式，将所有的文件操作定义为文件句柄的方法")
file = io.open("read.txt", "r")
print(file:read())
file:close()
file = io.open("test.txt", "a")
-- file:write("--test end\n")
file:close()

file = io.open("read.txt", "r")
print(file:seek())
print(file:seek("end", -5))
print(file:read(2))

print("我们可以使用两个函数：assert 和 error 来处理错误")

local function add(a,b)
   assert(type(a) == "number", "a 不是一个数字")
   assert(type(b) == "number", "b 不是一个数字")
   return a+b
end
-- add(10)

local function myError()
    error("there is an error")
end
local function callMyError()
    myError()
end
-- callMyError()

print("使用函数 pcall(protected call) 来包装需要执行的代码")
-- print(pcall(function(i) print(i) end, 33))
-- print(pcall(function(i) print(i) error('error..') end, 33))
print("debug.traceback：根据调用桟来构建一个扩展的错误消息")
-- xpcall(function() print("inside xpacll") error('error..') end, function() print(debug.traceback()) end)
function myFunction ()
   n = n/nil
end
function myErrorHandler( err )
   print( "ERROR:", err )
end
status = xpcall( myFunction, myErrorHandler )
print(status)

print("Lua 中的面向对象机制")
Shape = {area = 0}
function Shape:new(obj,side)
  obj = obj or {}
  setmetatable(obj, self)
  self.__index = self
  side = side or 0
  self.area = side*side;
  return obj
end

function Shape:printArea ()
  print("面积为 ",self.area)
end

myshape = Shape:new(nil,10)
myshape:printArea()

Square = Shape:new()
function Square:new(obj,side)
  obj = obj or Shape:new(obj,side)
  setmetatable(obj, self)
  self.__index = self
  return obj
end

function Square:printArea()
  print("正方形面积为 ", self.area)
end

mysquare = Square:new(nil,10)
mysquare:printArea()

Rectangle = Shape:new()
function Rectangle:new (obj, length, breadth)
  obj = obj or Shape:new(obj)
  setmetatable(obj, self)
  self.__index = self
  self.area = length * breadth
  return obj
end

function Rectangle:printArea ()
  print("矩形面积为 ",self.area)
end

myrectangle = Rectangle:new(nil,10,20)
myrectangle:printArea()

print("先编译和安装 luasql, 获得动态链接库 mysql.so")
luasql = require "luasql.mysql"
print(type(luasql))
print("创建环境对象 连接数据库 设置数据库的编码格式 执行数据库操作 文件对象的创建 关闭文件对象 关闭数据库连接 关闭数据库环境")
env = luasql.mysql()
conn = env:connect("my_test", "root", "Abc123#@!", "10.8.229.159", "63751") 
print(env, conn)
conn:execute("SET NAMES UTF8")
cur = conn:execute("select id, user_name from user limit 10")
row = cur:fetch({},"a")
file = io.open("db.txt","w+")
while row do
    var = string.format("%d %s\n", row.id, row.user_name)
    print(var)
    file:write(var)
    row = cur:fetch(row,"a")
end
file:close()
conn:close()
env:close()

-- C API





