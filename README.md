
[![Build Status](https://travis-ci.org/stackroute/boeing-wave3-syscop.svg?branch=v1.0.1)](https://travis-ci.org/stackroute/boeing-wave3-syscop)
[![codecov](https://codecov.io/gh/stackroute/boeing-wave3-syscop/branch/v1.0.1/graph/badge.svg)](https://codecov.io/gh/stackroute/boeing-wave3-syscop)
![](https://img.shields.io/codecov/c/github/stackroute/boeing-wave3-syscop.svg?style=flat)

![](https://img.shields.io/snyk/vulnerabilities/github/stackroute/boeing-wave3-syscop.svg?style=popout)
![](https://img.shields.io/github/issues/stackroute/boeing-wave3-syscop.svg?style=popout)

![](https://img.shields.io/github/contributors/stackroute/boeing-wave3-syscop.svg?style=popout)
![](https://img.shields.io/github/last-commit/stackroute/boeing-wave3-syscop.svg?style=popout)

![](https://img.shields.io/github/repo-size/stackroute/boeing-wave3-syscop.svg?style=popout)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

****Running the System****

Run ```mvn clean compile package``` on all the services








Product Name: SysCop

Problem statement: Build a non-Invasive Infrastructure Monitoring System IT Organizations have pockets of infrastructure comprising both legacy and new systems.  

Problem statement in details:
Monitoring such infrastructure is a challenge.  This is primarily due to the invasive nature of existing infrastructure monitoring tools in the market. Such monitoring systems often require adding instrumentation code to existing code base to emit monitoring metrics. Often this is not possible due to inaccessible source code, such as external vendor systems.  Also the pain and risks involved in updating, building, and redeploying a running system is too big to warrant for any such operations.    Another problem to identify and accept is that software monitoring is not only about detecting failure and reporting them - what almost all existing monitoring tools do. As a result, by the time failure is detected, reported, and then some action taken, the damage is already done. So the key challenge to solve is, how can a monitoring system intelligently predict a software failure instead of reporting after a failure? SysCop is an infrastructure monitoring system that can be attached to existing running systems and start monitoring them. The monitoring system is extensible to use in legacy application as well as modern containerized application infrastructure. SysCop is technology agnostic as it uses the concept of extensible metrics gathering agents.   SysCop have analytical capabilities to automatically detect and threat to the infrastructure. SysCop uses time-series data to recreate events on any point of time for analysis.

Services and their dbs:
1. Registration Service-mongo
2. Login (Both user and admin) Service-mySQL
3. Application Registration Service-mongo
4. Dashboard Service
5. Monitoring Service-Influx 
6. Data Collector Service
7. Alert Service
8. Processor Service-Influx
9. Agent Service-mongo
10. Threshold Service-mongo/sql

