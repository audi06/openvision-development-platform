SUMMARY = "Enigma2 plugin to manage your youtube account and watch videos"
DESCRIPTION = "Small plugin to manage your account, search and watch videos \
from youtube"
HOMEPAGE = "https://github.com/Taapat/enigma2-plugin-youtube"
SECTION = "multimedia"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://COPYING.GPLv2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/Taapat/enigma2-plugin-youtube.git \
        file://0001-Add_option_to_choose_style_of_VirtualKeyBoard.patch \
"

S = "${WORKDIR}/git"

inherit gitpkgv distutils-openplugins

PV = "git${SRCPV}"
PKGV = "git${GITPKGV}"

RDEPENDS_${PN} = " \
	python3-core \
	python3-codecs \
	python3-json \
	python3-netclient \
	python3-pyopenssl \
	python3-twisted-web \
	youtube-key \
	"

FILES_${PN} = "${libdir}"

RDEPENDS_{PN}-src += "${PN}"
FILES_${PN}-src = "${libdir}/enigma2/python/Plugins/Extensions/YouTube/*.py"
