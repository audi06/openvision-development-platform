FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_IGNORED = "\
	file://0001-ifupdown-support-post-up-pre-down-hooks.patch \
	file://0002-ifupdown-code-shrink.patch \
	file://0003-ifupdown-remove-interface-from-state_list-if-iface_u.patch \
	file://0004-ifupdown-support-metric-for-static-default-gw.patch \
	file://0005-ifupdown-improve-compatibility-with-Debian.patch \
	file://0006-get_linux_version_code-don-t-fail-on-3.0-foo.patch \
	file://0001-work-around-linux-ext2_fs.h-breakage.patch \
	file://0002-Create-and-use-our-own-copy-of-linux-ext2_fs.h.patch \
	file://0003-Drop-include-bb_linux_ext2_fs.h-use-existing-e2fspro.patch \
	file://0001-nandwrite-add-OOB-support.patch \
	file://0001-Revert-ip-fix-ip-oneline-a.patch \
	"

SRC_URI += "\
	file://mount_single_uuid.patch \
	file://use_ipv6_when_ipv4_unroutable.patch \
	file://inetd \
	file://inetd.conf \
	file://0001-Prevent-telnet-connections-from-the-internet-to-the-stb.patch \
	file://0002-Extended-network-interfaces-support.patch \
	file://ntp.script \
	"

# we do not really depend on mtd-utils, but as mtd-utils replaces 
# include/mtd/* we cannot build in parallel with mtd-utils
DEPENDS += "mtd-utils"

RDEPENDS_${PN} += "odhcp6c"

PACKAGES =+ "${PN}-inetd"
INITSCRIPT_PACKAGES += "${PN}-inetd"
INITSCRIPT_NAME_${PN}-inetd = "inetd.${BPN}" 
CONFFILES_${PN}-inetd = "${sysconfdir}/inetd.conf"
FILES_${PN}-inetd = "${sysconfdir}/init.d/inetd.${BPN} ${sysconfdir}/inetd.conf"
RDEPENDS_${PN}-inetd += "${PN}"
PROVIDES += "virtual/inetd"
RPROVIDES_${PN}-inetd += "virtual/inetd"
RCONFLICTS_${PN}-inetd += "xinetd"
RRECOMMENDS_${PN} += "${PN}-inetd"

pkg_postinst_${PN}_append () {
	update-alternatives --install /bin/sh sh /bin/busybox.nosuid 50
}

do_install_append() {
	sed -i "/[/][s][h]*$/d" ${D}${sysconfdir}/busybox.links.nosuid
	install -d ${D}${sysconfdir}/udhcpc.d
	install -m 0755 ${WORKDIR}/ntp.script ${D}${sysconfdir}/udhcpc.d/55ntp
}

do_configure_prepend_sh4 () {
	sed -i 's/^# CONFIG_FEATURE_SWAPON_PRI is not set/CONFIG_FEATURE_SWAPON_PRI=y/g' ${WORKDIR}/defconfig
}
