#!/usr/bin/lua

print("Hello World!")

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


