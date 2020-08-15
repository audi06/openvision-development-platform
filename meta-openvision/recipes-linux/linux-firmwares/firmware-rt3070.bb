require linux-firmware.inc

SRC_URI = "git://github.com/mdamt/linux-firmware.git;protocol=git"
SRC_URI = "git://github.com/BjornLee/linux-firmware.git;protocol=git"

S = "${WORKDIR}/git"

do_install() {
        install -d ${D}${nonarch_base_libdir}/firmware
        install -m 0644 rt3070.bin ${D}${nonarch_base_libdir}/firmware/
}

PACKAGES = "${PN}"
FILES_${PN} += "${nonarch_base_libdir}/firmware"
RDEPENDS_${PN} = "firmware-rt2870"

PACKAGE_ARCH = "all"
