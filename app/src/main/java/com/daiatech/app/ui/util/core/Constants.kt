/** Copyright (c) 2023 DAIA Tech Pvt Ltd */

package com.daiatech.app.ui.util.core

object Constants {
    const val MAX_PHONE_NUMBER_LENGTH = 13
    const val MIN_PHONE_NUMBER_LENGTH = 10
    val UPI_ID_REGEX = Regex("^(?!.*\\.\\.)(?!.*\\.$)(?!^\\.)[a-zA-Z0-9._-]+@[a-zA-Z0-9]+(?:\\.[a-zA-Z]{2,})?$")

    /** [DECIMAL_MATCH_REGEX] allows an empty string or a decimal input with leading or trailing zeros */
    const val FLOAT_MATCH_REGEX = "^\\d*\\.?\\d*|^\$"

    /** [INTEGER_MATCH_REGEX] allows an empty string or a decimal input with leading or trailing zeros */
    const val INTEGER_MATCH_REGEX = "(^[0-9]+\$|^\$)"

    const val OTP_LENGTH = 6

    /** Duration after which a user is allowed to request 'Resend OTP'  */
    const val OTP_TIMER_LENGTH_SECONDS = 30

    const val INACTIVITY_TIMER_LENGTH_SECONDS = 15

    const val UNIQUE_SYNC_WORK_NAME = "dashboard_sync_work"

    const val UPLOAD_ASSIGNMENTS_WORK_NAME = "upload_assignments_work"

    const val DOWNLOAD_ASSIGNMENTS_WORK_NAME = "download_assignments_work"

    const val INITIAL_TIME = "1970-01-01T00:00:00Z"

    const val MAX_AVATAR_CODE_LENGTH = 16
    const val MAX_PIN_CODE_LENGTH = 6

    /**
     * MAX_WAKE_LOCK_LENGTH = 20 minutes
     */
    const val MAX_WAKE_LOCK_LENGTH = 20 * 60 * 1000L

    const val DAY7_IN_SECONDS = 7 * 24 * 60 * 60

    const val ID_TOKEN_HEADER = "karya_worker_id_token"

    const val WA_HELP_PHONE_NUMBER = "912250972996"

    const val I_TAG = "itags"

    const val PROFILE = "_profile_"
    const val DEVICE_ID = "device_id"

    /** Duration from a previous sync after which a next automatic sync will occur.
     * FIXME: Currently, Automatic sync is not implemented. */
    const val SYNC_GAP_THRESHOLD_MS = 10800000

    const val MAX_MCQ_VERTICAL_OPTIONS = 5
    const val VISIBLE_MCQ_OPTIONS = 3

    const val VAANI_COORDINATOR_DASHBOARD_URL = "https://main-karya.centralindia.cloudapp.azure.com/mobile/reports/project/vaani_phase_2_Coordinator_Report"
    const val VAANI_COORDINATOR_TAG = "co-ordinator"

    const val MAIN_ACTIVITY_PACKAGE_NAME = "com.daiatech.app.ui.MainActivity"

    /**
     * Default timeout to wait for OTP receive event
     */
    const val OTP_READ_TIMEOUT_MS = 12000L

    const val SPOTLIGHT_KEY_HELP_BUTTON = "key_help_button"

    const val KARYA_OPERATIONS_EMAIL = "operations@karya.in"
}
