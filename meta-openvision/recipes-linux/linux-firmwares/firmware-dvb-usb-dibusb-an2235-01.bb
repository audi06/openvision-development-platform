require linux-firmware.inc

DESCRIPTION = "Firmware for dvb-usb-dibusb-an2235-01"

do_install() {
	install -d ${D}${nonarch_base_libdir}/firmware
	install -m 0644 dvb-usb-dibusb-an2235-01.fw ${D}${nonarch_base_libdir}/firmware
}
