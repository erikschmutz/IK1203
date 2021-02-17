telnet 192.168.3.16 pop3

# HERE WE HAVE A EXAMPLE transcript which logs in and retrives an email.
# Escape character is '^]'.
# +OK Hello there.
# User user1
# +OK Password required.
# PASS user1
# +OK logged in.
# RETR
# -ERR Invalid message number.
# LIST  
# +OK POP3 clients that break here, they violate STD53.
# 1 293
# 2 333
# 3 323
# 4 288
# 5 312
# 6 305
# 7 405
# 8 295
# 9 326
# 10 309
# 11 442

# .
# RETR 1  
# +OK 293 octets follow.
# Return-Path: <user1@private.lab>
# X-Original-To: user1@private.lab
# Delivered-To: user1@private.lab
# Received: from mail.private.lab (unknown [192.168.3.114])
#         by mail.private.lab (Postfix) with SMTP id 1B0FD63382
#         for <user1@private.lab>; Fri, 24 Jan 2020 21:13:02 +0100 (CET)

# Halloj