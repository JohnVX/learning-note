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


