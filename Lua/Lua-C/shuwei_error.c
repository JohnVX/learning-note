//
// Created by wangsw on 2020/9/25.
//

#include <stdarg.h>
#include <stdio.h>
#include <stdlib.h>
#include "lua.h"

void error (lua_State *L, const char *fmt, ...) {
    va_list argp;
    va_start(argp, fmt);
    vfprintf(stderr, fmt, argp);
    va_end(argp);
    lua_close(L);
    exit(EXIT_FAILURE);
}