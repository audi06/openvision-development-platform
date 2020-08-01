SUMMARY = "Filemanager MoviePlayer Extentions"
MAINTAINER = "Coolman, Betonme & Swiss-MAD"
SECTION = "extra"
PRIORITY = "optional"

require conf/license/license-gplv2.inc

RDEPENDS_${PN} = "gstreamer1.0-plugins-good-flv gstreamer1.0-plugins-bad-rtmp python3-json python3-html python3-requests python-mutagen rtmpdump"

inherit gitpkgv python3native gettext autotools-brokensep rm_python_pyc compile_python_pyo no_python_src

PV = "4.0.+git${SRCPV}"
PKGV = "4.0.+git${GITPKGV}"

SRC_URI = "git://github.com/betonme/e2openplugin-EnhancedMovieCenter.git \
        file://get-rid-of-sFileSize.patch \
"

S = "${WORKDIR}/git"

FILES_${PN} = "${sysconfdir} ${libdir}"

FILES_${PN}-src = "\
	${libdir}/enigma2/python/Components/Converter/*.py \
	${libdir}/enigma2/python/Components/Renderer/*.py \
	${libdir}/enigma2/python/Components/Sources/*.py \
	${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/*.py \
	${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/locale/*/*/*.po \
	"

EXTRA_OECONF = "\
    BUILD_SYS=${BUILD_SYS} \
    HOST_SYS=${HOST_SYS} \
    STAGING_INCDIR=${STAGING_INCDIR} \
    STAGING_LIBDIR=${STAGING_LIBDIR} \
"

CONFFILES_${PN} = "${sysconfdir}/enigma2/emc-hide.cfg ${sysconfdir}/enigma2/emc-noscan.cfg ${sysconfdir}/enigma2/emc-permsort.cfg ${sysconfdir}/enigma2/emc-topdir.cfg"

do_populate_sysroot[noexec] = "1"
do_package_qa[noexec] = "1"

pkg_postrm_${PN}() {
#!/bin/sh
rm -rf ${libdir}/enigma2/python/Plugins/Extensions/EnhancedMovieCenter/
echo "Plugin removed! You should restart enigma2 now!"
exit 0
}
