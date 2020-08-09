SUMMARY = "E2i Player for E2"
DESCRIPTION = "E2i Player for E2"
SECTION = "multimedia"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://LICENSE;md5=84dcc94da3adb52b53ae4fa38fe49e5d"

SRC_URI = "git://github.com/persianpros/e2iplayer.git;protocol=http"

S = "${WORKDIR}/git"

inherit gitpkgv allarch distutils-openplugins gettext

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

DEPENDS = "gettext-native python3"

RDEPENDS_${PN} = " \
	cmdwrapper \
	duktape \
	f4mdump \
	ffmpeg \
	hlsdl \
	iptvsubparser \
	lsdir \
	python3-compression \
	python3-core \
	python3-e2icjson \
	python3-html \
	python3-json \
	python3-pycurl \
	python3-shell \
	rtmpdump \
	uchardet \
	wget \
	${@bb.utils.contains("MACHINE_FEATURES", "libeplayer", "", "exteplayer3 gst-ifdsrc gstplayer", d)} \
	"

deltask package_qa

FILES_${PN} += "${sysconfdir}"

do_install_append() {
    install -d ${D}${sysconfdir}
    cp -r  --preserve=mode,links ${S}/vk ${D}${sysconfdir}/vk
}
