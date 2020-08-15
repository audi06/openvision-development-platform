DESCRIPTION = "Imports XMLTV and epg.dat files into the EPG cache of enigma2"
MAINTAINER = "OpenPLi team"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://..${sysconfdir}/epgimport/readme.txt;startline=1;endline=4;md5=c162054328d930d453543efef81be1d8"

inherit gitpkgv distutils-openplugins

PV = "1.0+git${SRCPV}"
PKGV = "1.0+git${GITPKGV}"

GITHUB_URI ?= "git://github.com"
SRC_URI = "${GITHUB_URI}/OpenPLi/${BPN}.git"

S = "${WORKDIR}/git/src"

DEPENDS = "python3"
RDEPENDS_${PN} = "python3-compression python3-shell python3-lzma python3-pkgutil"
RRECOMMENDS_${PN} = "${PN}-rytec"
PACKAGES = "${PN}-dbg ${PN}"

RREPLACES_${PN} = "enigma2-plugin-extensions-xmltvimport"
RCONFLICTS_${PN} = "enigma2-plugin-extensions-xmltvimport"

PLUGIN = "EPGImport"

FILES_${PN} = "${libdir}/enigma2/python"
FILES_${PN}-dbg = "${libdir}/enigma2/python/Plugins/Extensions/${PLUGIN}/.debug ${prefix}/src/debug"

pkg_postinst_${PN}() {

	if [ ! -f $D${sysconfdir}/image-version ]
	then
		# when slipstreaming, don't patch enigma
		exit 0
	fi
	if grep -q Vision $D${sysconfdir}/image-version
	then
		# Vision needs no patch...
		true
	else
		[ -f $D${bindir}/enigma2.sh.xmltvbak ] || {
			cp $D${bindir}/enigma2.sh $D${bindir}/enigma2.sh.xmltvbak
			sed '3ipython $D${libdir}/enigma2/python/Plugins/Extensions/EPGImport/boot.py' $D${bindir}/enigma2.sh.xmltvbak > $D${bindir}/enigma2.sh
		}
	fi
}

pkg_prerm_${PN}() {
	if [ -f ${bindir}/enigma2.sh.xmltvbak ] ; then
		mv -f ${bindir}/enigma2.sh.xmltvbak ${bindir}/enigma2.sh
	fi
}
