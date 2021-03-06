package eu.chargetime.ocpp.test.profiles.firmware.json
/*
    ChargeTime.eu - Java-OCA-OCPP

    MIT License

    Copyright (C) 2016-2018 Thomas Volden <tv@chargetime.eu>
    Copyright (C) 2018 Mikhail Kladkevich <kladmv@ecp-share.com>

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */

import eu.chargetime.ocpp.model.firmware.FirmwareStatus
import eu.chargetime.ocpp.test.base.json.JSONBaseSpec
import spock.util.concurrent.PollingConditions

class JSONFirmwareStatusNotificationSpec extends JSONBaseSpec {

    def "Charge point sends a FirmwareStatusNotification request and receives a response"() {
        def conditions = new PollingConditions(timeout: 1)
        given:
        conditions.eventually {
            assert centralSystem.connected()
        }

        when:
        chargePoint.sendFirmwareStatusNotificationRequest(FirmwareStatus.Downloading)

        then:
        conditions.eventually {
            assert centralSystem.hasHandledFirmwareStatusNotificationRequest()
            assert chargePoint.hasReceivedFirmwareStatusNotificationConfirmation()
        }
    }
}
