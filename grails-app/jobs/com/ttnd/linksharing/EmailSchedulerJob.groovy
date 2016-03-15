package com.ttnd.linksharing



class EmailSchedulerJob {

    def userService

    static triggers = {
      cron name: 'emailCron', cronExpression: "0 0 1 ? * 2"
    }

    def execute() {
        userService.sendUnreadItemsEmail()
    }
}
